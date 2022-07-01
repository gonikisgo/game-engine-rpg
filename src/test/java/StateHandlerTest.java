import cz.cvut.fel.pjv.handlers.StateHandler;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.objects.mobs.friends.Elf;
import cz.cvut.fel.pjv.objects.stat1c.Tree;
import cz.cvut.fel.pjv.objects.stat1c.weapon.BasicWeapon;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kiselnik
 */

public class StateHandlerTest {
    private final static int offsetX = 10; // 10 tiles x offset
    private final static int offsetY = 8; // 8 tiles y offset
    GamePanel gamePanel = new GamePanel("src/test/java/test.json");
    BasicMob basicMob;
    private StateHandler mockStateHandler;

    @BeforeEach
    void setUp() {
        mockStateHandler = new StateHandler(gamePanel);
        basicMob = new BasicMob();
        basicMob.collision = false;
        basicMob.delta = 3;
        basicMob.speed = 10;
        basicMob.setDefault(offsetX + 2, offsetY);
        basicMob.setMoveArea();
        basicMob.solidAreaX = 0;
        basicMob.solidAreaY = 0;
        basicMob.solidAreaWidthX = GamePanel.tileSize;
        basicMob.solidAreaHeightY = GamePanel.tileSize;
        basicMob.solidArea = new Rectangle(basicMob.solidAreaX, basicMob.solidAreaY, basicMob.solidAreaWidthX, basicMob.solidAreaHeightY);
    }

    @Test
    public void testCheckWorldCollisionUp() {
        basicMob.moveDirection = "up";
        mockStateHandler.checkWorldCollision(basicMob, basicMob.upBorder, basicMob.leftBorder, basicMob.downBorder, basicMob.rightBorder);
        assertTrue(basicMob.collision);
    }

    @Test
    public void testCheckWorldCollisionDown() {
        basicMob.moveDirection = "down";
        mockStateHandler.checkWorldCollision(basicMob, basicMob.upBorder, basicMob.leftBorder, basicMob.downBorder, basicMob.rightBorder);
        assertFalse(basicMob.collision);
    }

    @Test
    public void testCheckCollWithObjects() {
        gamePanel.allObjects.add(new Tree(gamePanel));
        gamePanel.allObjects.get(gamePanel.allObjects.size() - 1).objectWorldX = GamePanel.tileSize * (offsetX + 2);
        gamePanel.allObjects.get(gamePanel.allObjects.size() - 1).objectWorldY = GamePanel.tileSize * (offsetY + 1);
        basicMob.moveDirection = "down";
        mockStateHandler.checkCollWithObjects(basicMob);
        assertTrue(basicMob.collision);
    }

    @Test
    public void testCheckCollWithMobs() {
        gamePanel.allMobs.add(new Elf(gamePanel));
        gamePanel.allMobs.get(gamePanel.allMobs.size() - 1).setDefault(offsetX + 1, offsetY);
        gamePanel.allMobs.get(gamePanel.allMobs.size() - 1).setMoveArea();
        basicMob.moveDirection = "left";
        mockStateHandler.checkCollWithMobs(basicMob, -1);
        assertTrue(basicMob.collision);
    }

    @Test
    public void testCheckCollWithPlayer() {
        gamePanel.player.objectWorldX = (offsetX + 3) * GamePanel.tileSize;
        gamePanel.player.objectWorldY = offsetY * GamePanel.tileSize;
        basicMob.moveDirection = "right";
        mockStateHandler.checkCollWithPlayer(basicMob);
        assertTrue(basicMob.collision);
    }

    // left tree destroy attempt check
    @Test
    public void testCheckWeapWithObjects() {
        BasicWeapon basicWeapon = new Sword(gamePanel);
        basicWeapon.objectWorldX = (offsetX + 2) * GamePanel.tileSize + basicWeapon.playerHandOffset - basicWeapon.solidAreaWidthX;
        basicWeapon.objectWorldY = offsetY * GamePanel.tileSize + basicWeapon.solidAreaY;
        gamePanel.allObjects.add(new Tree(gamePanel));
        gamePanel.allObjects.get(gamePanel.allObjects.size() - 1).objectWorldX = GamePanel.tileSize * (offsetX + 1);
        gamePanel.allObjects.get(gamePanel.allObjects.size() - 1).objectWorldY = GamePanel.tileSize * (offsetY);
        assertEquals(mockStateHandler.checkWeapWithObjects(basicWeapon), gamePanel.allObjects.size() - 1);
    }

    // up attack check
    @Test
    public void testCheckWeapWithMobs() {
        BasicWeapon basicWeapon = new Sword(gamePanel);
        basicWeapon.objectWorldX = (offsetX + 2) * GamePanel.tileSize + basicWeapon.solidAreaY;
        basicWeapon.objectWorldY = (offsetY + 1) * GamePanel.tileSize + basicWeapon.playerHandOffset - basicWeapon.solidAreaWidthX;
        gamePanel.allMobs.add(basicMob);
        assertEquals(mockStateHandler.checkWeapWithMobs(basicWeapon), gamePanel.allMobs.size() - 1);
    }

    @Test
    public void testCheckMobsActArea() {
        gamePanel.player.objectWorldX = (offsetX + 3) * GamePanel.tileSize;
        gamePanel.player.objectWorldY = offsetY * GamePanel.tileSize;
        gamePanel.allMobs.add(basicMob);
        assertTrue(mockStateHandler.checkMobsActArea(gamePanel.allMobs.size() - 1));
    }
}
