package cz.cvut.fel.pjv.screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
UI panel for showing player's health and speed
 */
public class PanelHealth extends JPanel {
    final static int imageSize = 24;
    BufferedImage imageHeart1, imageHeart2, imageHeart3, imageSpeed;
    GamePanel gamePanel;
    int defaultX = 6;
    int defaultY = 6;
    Font customFont = new Font(null, Font.PLAIN, 32);

    public PanelHealth(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(640, 48));
        setUiImages();
    }

    public void setUiImages() {
        try {
            imageHeart1 = ImageIO.read(getClass().getResourceAsStream("/objects/heart1.png"));
            imageHeart2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart2.png"));
            imageHeart3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart3.png"));
            imageSpeed = ImageIO.read(getClass().getResourceAsStream("/objects/speed.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 640, 48);
        drawHealthBar(graphics2D);
        defaultX = 6;
        drawSpeed(graphics2D);
        defaultX = 6;
    }

    private void drawHealthBar(Graphics2D graphics2D) {
        gamePanel.player.health = gamePanel.player.health >= 100 ? 100 : gamePanel.player.health;
        int i = 0;
        for (; i < gamePanel.player.health / 10; i++) {
            graphics2D.drawImage(imageHeart1, defaultX, defaultY, imageSize, imageSize, null);
            defaultX += 28;
        }
        if (gamePanel.player.health % 10 != 0) {
            graphics2D.drawImage(imageHeart2, defaultX, defaultY, imageSize, imageSize, null);
            defaultX += 28;
            i++;
        }
        for (int j = 10 - i; j > 0; j--) {
            graphics2D.drawImage(imageHeart3, defaultX, defaultY, imageSize, imageSize, null);
            defaultX += 28;
        }
    }

    private void drawSpeed(Graphics2D graphics2D) {
        for (int j = 0; j < gamePanel.player.speed * 3; j++) {
            graphics2D.drawImage(imageSpeed, defaultX, defaultY + 30, 10, 6, null);
            defaultX += 9;
        }
    }
}
