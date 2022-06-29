package cz.cvut.fel.pjv.screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameUI {
    public StateRect stateRect;
    BufferedImage imageXP;
    BufferedImage imagePotion;
    BufferedImage imageKey;
    BufferedImage imageCoin;
    GamePanel gamePanel;
    public boolean notify = false;
    String notification = null;
    Font customFont = new Font(null, Font.PLAIN, 20);
    int displayTimeCount = 0;

    public GameUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setUiImages();
    }

    public void setUiImages() {
        try {
            // imageXP = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
            imagePotion = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
            imageKey = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            imageCoin = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(customFont);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("Keys: " + gamePanel.player.keys, 10, 40);
        graphics2D.drawImage(imageKey, 80, 15, gamePanel.tileSize, gamePanel.tileSize, null);
        if (notify) {

            displayTimeCount++;
            graphics2D.drawString(notification, 130, 40);

            if (displayTimeCount > 50) {
                notify = false;
                displayTimeCount = 0;
            }
        }
    }

    public void setMessage(String message) {
        notify = true;
        notification = message;
    }
}
