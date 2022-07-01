package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * key class
 * @author kiselnik
 */


public class Key extends BasicObject {
    private final static Logger LOGGER = Logger.getLogger(Key.class.getName());
    public Key(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Key";
        solidAreaX = 2;
        solidAreaY = 2;
        solidAreaWidthX = 28;
        solidAreaHeightY = 28;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/forest/key.png"));
                    break;
                case "Snow":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/snow/key.png"));
                    break;
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "key image wasn't loaded", ex);
        }
    }
}
