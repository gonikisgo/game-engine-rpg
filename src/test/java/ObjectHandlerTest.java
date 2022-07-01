import cz.cvut.fel.pjv.handlers.ObjectHandler;
import cz.cvut.fel.pjv.objects.mobs.enemies.BossMonster;
import cz.cvut.fel.pjv.objects.stat1c.Door;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author kiselnik
 */

public class ObjectHandlerTest {
    GamePanel gamePanel = new GamePanel("src/test/java/test.json");
    private ObjectHandler mockObjectHandler;

    @BeforeEach
    void setUp() {
        mockObjectHandler = new ObjectHandler(gamePanel);
        gamePanel.player.frequency = 4;
        gamePanel.player.keys = 1;
        gamePanel.allObjects.clear();
        gamePanel.player.collisionIndex = 0;
        gamePanel.allObjects.add(new Door(gamePanel));

        gamePanel.player.health = 20;
        gamePanel.allMobs.clear();
        gamePanel.allMobs.add(new BossMonster(gamePanel));
        gamePanel.player.currentWeapon = new Sword(gamePanel);
    }

    @Test
    public void testPlayerObjectInteraction() {
        int tmpKeys = gamePanel.player.keys;
        mockObjectHandler.playerObjectInteraction();
        assertEquals(gamePanel.player.keys, tmpKeys - 1);
        assertEquals(gamePanel.allObjects.size(), 0);
    }

    @Test
    public void testPlayerMobInteraction() {
        mockObjectHandler.playerMobInteraction();
        assertEquals(gamePanel.player.health, 0);
    }

    @Test
    public void testWeaponMobInteraction() {
        int tmpSpeed = gamePanel.allMobs.get(0).speed;
        int tmpHealth = gamePanel.allMobs.get(0).health;
        int tmpTotalDamageOnMonster = gamePanel.totalDamageOnMonster;
        mockObjectHandler.weaponMobInteraction();
        assertTrue(gamePanel.allMobs.get(0).underAttack);
        assertEquals(gamePanel.allMobs.get(0).speed, tmpSpeed + 1);
        assertEquals(gamePanel.allMobs.get(0).health, tmpHealth - gamePanel.player.currentWeapon.damage);
        assertEquals(gamePanel.totalDamageOnMonster, tmpTotalDamageOnMonster + gamePanel.player.currentWeapon.damage);
    }
}
