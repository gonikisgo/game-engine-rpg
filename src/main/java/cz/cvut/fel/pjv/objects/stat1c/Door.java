package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
door class
 */
public class Door extends BasicObject {

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
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
