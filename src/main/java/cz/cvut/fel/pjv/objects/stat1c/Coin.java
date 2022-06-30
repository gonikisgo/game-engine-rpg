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
coin class
 */

public class Coin extends BasicObject {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public Coin(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Coin";
        solidAreaX = 6;
        solidAreaY = 6;
        solidAreaWidthX = 20;
        solidAreaHeightY = 20;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/forest/coin.png"));
                    break;
                case "Snow":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/objects/snow/coin.png"));
                    break;
            }
            LOGGER.log(Level.INFO, "coin image was loaded");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "coin image wasn't loaded", ex);
        }
    }
}
