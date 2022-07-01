package cz.cvut.fel.pjv.objects.mobs.enemies;

import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
monster class
 */

public class Monster extends BasicMob {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public Monster(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        health = 100;

        name = "Monster";
        collision = true;
        speed = 2;
        delta = 2;
        solidAreaX = 5;
        solidAreaY = 5;
        solidAreaWidthX = 22;
        solidAreaHeightY = 22;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/monster/right2.png"));
                    break;

                case "Snow":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/monster/right2.png"));
                    break;
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "monster images weren't loaded", ex);
        }
    }
}
