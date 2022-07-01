import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * @author kiselnik
 */

public class WeaponHandlerTest {
    GamePanel gamePanel = new GamePanel("src/test/java/test.json");
    private WeaponHandler mockWeaponHandler;

    @BeforeEach
    void setUp() {
        mockWeaponHandler = new WeaponHandler(gamePanel);
        gamePanel.player.coins = 5;
        gamePanel.keyHandler.buyWeapon = true;
    }

    @Test
    public void testWeaponBuy() {
        mockWeaponHandler.weaponBuyCheck();
        assertEquals(gamePanel.player.weapon[0].getClass(), Sword.class);
        assertNull(gamePanel.player.weapon[1]);
    }

    @Test
    public void testWeaponChoose() {
        mockWeaponHandler.weaponChoice();
        assertNull(gamePanel.player.currentWeapon);
    }
}
