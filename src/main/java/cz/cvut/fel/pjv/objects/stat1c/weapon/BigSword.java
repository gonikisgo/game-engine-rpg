package cz.cvut.fel.pjv.objects.stat1c.weapon;

import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * big sword class
 * @author kiselnik
 */

public class BigSword extends BasicWeapon {
    private final static Logger LOGGER = Logger.getLogger(BigSword.class.getName());
    public BigSword(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.damage = 25;

        solidAreaX = 0;
        solidAreaY = 9;
        solidAreaWidthX = 27;
        solidAreaHeightY = 15;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_right.png"));
                    break;

                case "Snow":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_right.png"));
                    break;
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "big sword's images weren't loaded", ex);
        }
    }
}
