package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
water class
 */


public class Water extends BasicObject {
    public Water(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Water";
        solidAreaX = 0;
        solidAreaY = 0;
        solidAreaWidthX = 32;
        solidAreaHeightY = 32;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
                    collision = true;
                    break;
                case "Snow":
                    this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/ice.png"));
                    break;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
