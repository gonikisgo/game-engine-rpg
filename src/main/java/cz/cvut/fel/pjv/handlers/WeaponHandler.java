package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.objects.stat1c.weapon.BigSword;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class for choosing and buying weapon
 * @author kiselnik
 */

public class WeaponHandler {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    GamePanel gamePanel;

    public WeaponHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void weaponBuyCheck() {
        // checking if player want to buy weapon
        if (gamePanel.keyHandler.buyWeapon) {
            gamePanel.keyHandler.buyWeapon = false;
            // checking which weapon is wanted
            if (gamePanel.keyHandler.isMenuBuyBigSword && gamePanel.player.weapon[1] == null) {
                // if player has enough coins for it
                if (gamePanel.player.coins >= 25) {
                    gamePanel.player.coins -= 25;
                    gamePanel.player.weapon[1] = new BigSword(gamePanel);
                    LOGGER.log(Level.INFO, "big sword is bought");
                } else {
                    LOGGER.log(Level.INFO, "attempt buying big sword, not enough coins");
                }
            } else if (!gamePanel.keyHandler.isMenuBuyBigSword && gamePanel.player.weapon[0] == null) {
                // if player has enough coins for it
                if (gamePanel.player.coins >= 5) {
                    gamePanel.player.coins -= 5;
                    gamePanel.player.weapon[0] = new Sword(gamePanel);
                    LOGGER.log(Level.INFO, "sword is bought");
                } else {
                    LOGGER.log(Level.INFO, "attempt buying sword, not enough coins");
                }
            }
        }
    }

    public void weaponChoice() {
        if (gamePanel.keyHandler.isBigSwordChoice) {
            if (gamePanel.player.weapon[1] != null) {
                gamePanel.player.currentWeapon = gamePanel.player.weapon[1];
            } else {
                gamePanel.player.currentWeapon = null;
            }
        } else {
            if (gamePanel.player.weapon[0] != null) {
                gamePanel.player.currentWeapon = gamePanel.player.weapon[0];
            } else {
                gamePanel.player.currentWeapon = null;
            }
        }
    }
}
