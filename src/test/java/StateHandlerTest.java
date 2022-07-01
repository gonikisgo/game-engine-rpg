import cz.cvut.fel.pjv.handlers.StateHandler;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.objects.mobs.friends.Elf;
import cz.cvut.fel.pjv.objects.stat1c.Tree;
import cz.cvut.fel.pjv.objects.stat1c.Water;
import cz.cvut.fel.pjv.screen.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class StateHandlerTest {
    GamePanel gamePanel = new GamePanel("src/test/java/test.json");
    private StateHandler mockStateHandler;
    BasicMob basicMob;

    @BeforeEach
    void setUp() {
        mockStateHandler = new StateHandler(gamePanel);
        basicMob = new BasicMob();
        basicMob.collision = false;
        basicMob.delta = 3;
        basicMob.speed = 16;
        basicMob.solidAreaX = 0;
        basicMob.solidAreaY = 0;
        basicMob.solidAreaWidthX = 32;
        basicMob.solidAreaHeightY = 32;
        basicMob.solidArea = new Rectangle(basicMob.solidAreaX, basicMob.solidAreaY, basicMob.solidAreaWidthX, basicMob.solidAreaHeightY);
    }

    @Test
    public void testCheckWorldCollisionUp() {
        basicMob.setDefault(20, 8);
        basicMob.setMoveArea();
        basicMob.moveDirection = "up";
        mockStateHandler.checkWorldCollision(basicMob, basicMob.upBorder, basicMob.leftBorder, basicMob.downBorder, basicMob.rightBorder);
        assertTrue(basicMob.collision);
    }

    @Test
    public void testCheckWorldCollisionDown() {
        basicMob.setDefault(20, 8);
        basicMob.setMoveArea();
        basicMob.moveDirection = "down";
        mockStateHandler.checkWorldCollision(basicMob, basicMob.upBorder, basicMob.leftBorder, basicMob.downBorder, basicMob.rightBorder);
        assertFalse(basicMob.collision);
    }

    @Test
    public void testCheckCollWithObjects() {
        basicMob.setDefault(20, 8);
        basicMob.setMoveArea();
        gamePanel.allObjects.add(new Tree(gamePanel));
        gamePanel.allObjects.get(gamePanel.allObjects.size() - 1).objectWorldX = GamePanel.tileSize * (20);
        gamePanel.allObjects.get(gamePanel.allObjects.size() - 1).objectWorldY = GamePanel.tileSize * (9);
        basicMob.moveDirection = "down";
        mockStateHandler.checkCollWithObjects(basicMob);
        assertTrue(basicMob.collision);
    }

    @Test
    public void testCheckCollWithMobs() {
        basicMob.setDefault(18, 0);
        basicMob.setMoveArea();
        basicMob.moveDirection = "left";
        mockStateHandler.checkCollWithMobs(basicMob, -1);
        assertTrue(basicMob.collision);
    }
}
