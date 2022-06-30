package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.objects.stat1c.weapon.BigSword;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;

/*
class for choosing and buying weapon
 */

public class WeaponHandler {
    GamePanel gamePanel;

    public WeaponHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void weaponBuyCheck() {
        if (gamePanel.keyHandler.buyWeapon) {
            if (gamePanel.keyHandler.isMenuBigSword && gamePanel.player.weapon[1] == null) {
                if (gamePanel.player.coins >= 25) {
                    gamePanel.player.coins -= 25;
                    gamePanel.player.weapon[1] = new BigSword(gamePanel);
                } else {
                    // System.out.println("Not enough to 25 coins");
                }
            } else if (!gamePanel.keyHandler.isMenuBigSword && gamePanel.player.weapon[0] == null) {
                if (gamePanel.player.coins >= 5) {
                    gamePanel.player.coins -= 5;
                    gamePanel.player.weapon[0] = new Sword(gamePanel);
                } else {
                    // System.out.println("Not enough to 5 coins");
                }
            }
            gamePanel.keyHandler.buyWeapon = false;
        }
    }

    public void weaponChoice() {
        if (gamePanel.keyHandler.weaponChoice) {
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
