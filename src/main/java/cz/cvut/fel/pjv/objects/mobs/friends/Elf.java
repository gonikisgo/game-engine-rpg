package cz.cvut.fel.pjv.objects.mobs.friends;

import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
elf class
 */
public class Elf extends BasicMob {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public Elf(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Elf";
        collision = true;
        speed = 1;
        delta = 4;
        solidAreaX = 6;
        solidAreaY = 4;
        solidAreaWidthX = 18;
        solidAreaHeightY = 24;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);

        canHeal = gamePanel.rand.nextInt(2) == 0;
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/elf/right2.png"));
                    break;

                case "Snow":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/elf/right2.png"));
                    break;
            }
            LOGGER.log(Level.INFO, "elf images were loaded");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "elf images weren't loaded", ex);
        }
    }
}
