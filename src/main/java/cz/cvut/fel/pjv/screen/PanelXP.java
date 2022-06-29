package cz.cvut.fel.pjv.screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PanelXP extends JPanel {
    BufferedImage imageHeart1;
    BufferedImage imageHeart2;
    BufferedImage imageSpeed;
    GamePanel gamePanel;
    int defaultX = 6;
    int defaultY = 6;

    public PanelXP(GamePanel gamePanel) {
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
            imageSpeed = ImageIO.read(getClass().getResourceAsStream("/objects/speed.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 640, 48);

        int tmpNum = gamePanel.player.health > 220 ? 22 : gamePanel.player.health / 10;
        for (int i = 0; i < tmpNum; i++) {
            graphics2D.drawImage(imageHeart1, defaultX, defaultY, 24, 24, null);
            defaultX += 28;
        }
        if (gamePanel.player.health % 10 == 5) {
            graphics2D.drawImage(imageHeart2, defaultX, defaultY, 24, 24, null);
        }
        defaultX = 6;

        tmpNum = gamePanel.player.speed > 21 ? 63 : gamePanel.player.speed * 3;
        for (int i = 0; i < tmpNum; i++) {
            graphics2D.drawImage(imageSpeed, defaultX, defaultY + 30, 10, 6, null);
            defaultX += 10;
        }
        defaultX = 6;
    }
}
