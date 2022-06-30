package cz.cvut.fel.pjv.objects.stat1c.weapon;

import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
sword class
 */

public class Sword extends BasicWeapon {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public Sword(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.damage = 7;

        solidAreaX = 0;
        solidAreaY = 9;
        solidAreaWidthX = 23;
        solidAreaHeightY = 15;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_right.png"));
                    break;

                case "Snow":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_right.png"));
                    break;
            }
            LOGGER.log(Level.INFO, "sword's images were loaded");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "sword's images weren't loaded", ex);
        }
    }
}
