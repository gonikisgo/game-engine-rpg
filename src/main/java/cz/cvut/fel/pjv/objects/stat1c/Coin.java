package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
coin class
 */

public class Coin extends BasicObject {
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
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
