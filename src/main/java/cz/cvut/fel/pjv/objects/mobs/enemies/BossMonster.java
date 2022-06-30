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
boss monster class
 */
public class BossMonster extends BasicMob {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public BossMonster(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        health = 170;

        name = "BossMonster";
        collision = true;
        speed = 7;
        delta = 20;
        solidAreaX = 5;
        solidAreaY = 5;
        solidAreaWidthX = 22;
        solidAreaHeightY = 22;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/boss_monster/right2.png"));
                    break;

                case "Snow":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/boss_monster/right2.png"));
                    break;
            }
            LOGGER.log(Level.INFO, "boss monster images were loaded");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "boss monster images weren't loaded", ex);
        }
    }
}
