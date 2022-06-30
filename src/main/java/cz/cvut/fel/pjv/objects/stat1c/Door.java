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
door class
 */
public class Door extends BasicObject {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());

    public Door(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Door";
        collision = true;
        solidAreaX = 2;
        solidAreaY = 2;
        solidAreaWidthX = 28;
        solidAreaHeightY = 28;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/forest/door.png"));
                    break;
                case "Snow":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/snow/door.png"));
                    break;
            }
            LOGGER.log(Level.INFO, "door image was loaded");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "door image wasn't loaded", ex);
        }
    }
}
