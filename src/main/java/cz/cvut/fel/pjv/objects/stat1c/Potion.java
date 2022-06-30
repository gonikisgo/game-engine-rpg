package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
potion class
 */
public class Potion extends BasicObject {
    public Potion(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Potion";
        solidAreaX = 4;
        solidAreaY = 3;
        solidAreaWidthX = 24;
        solidAreaHeightY = 26;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);

        int potionColor = gamePanel.rand.nextInt(2);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    switch (potionColor) {
                        case 0:
                            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/forest/potion1.png"));
                            break;
                        case 1:
                            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/forest/potion2.png"));
                            break;
                    }
                    break;
                case "Snow":
                    switch (potionColor) {
                        case 0:
                            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/snow/potion1.png"));
                            break;
                        case 1:
                            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/snow/potion2.png"));
                            break;
                    }
                    break;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
