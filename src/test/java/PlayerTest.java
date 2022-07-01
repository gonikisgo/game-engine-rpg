import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author kiselnik
 */

public class PlayerTest {
    GamePanel gamePanel = new GamePanel("src/test/java/test.json");
    KeyHandler keyHandler = new KeyHandler();
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockPlayer = new Player(gamePanel, keyHandler);
        mockPlayer.moveDirection = "downAttack";
        mockPlayer.attacking = true;
        mockPlayer.currentWeapon = new Sword(gamePanel);
    }

    // checking player image updating for sword attacking case
    @Test
    public void testUpdatePlayerImage() {
        mockPlayer.updatePlayerImage();
        assertEquals(mockPlayer.image, mockPlayer.currentWeapon.downAttack);
    }
}