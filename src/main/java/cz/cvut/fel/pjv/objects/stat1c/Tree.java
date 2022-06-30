package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
tree class
 */

public class Tree extends BasicObject {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public Tree(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        health = 60;

        name = "Tree";
        collision = true;
        solidAreaX = 3;
        solidAreaY = 2;
        solidAreaWidthX = 26;
        solidAreaHeightY = 28;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);

        canBeDestroyed = gamePanel.rand.nextInt(2) == 0;
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/forest/tree.png"));
                    break;
                case "Snow":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/snow/tree.png"));
                    break;
            }
            LOGGER.log(Level.INFO, "tree image was loaded");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "tree image wasn't loaded", ex);
        }
    }
}
