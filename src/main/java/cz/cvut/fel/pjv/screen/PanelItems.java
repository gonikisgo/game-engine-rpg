package cz.cvut.fel.pjv.screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PanelItems extends JPanel implements ActionListener {
    Font customFont = new Font(null, Font.PLAIN, 18);
    BufferedImage imagePotion;
    BufferedImage imageKey;
    BufferedImage imageCoin;
    GamePanel gamePanel;

    JButton pauseButton, endButton;
    int defaultX = 655;
    int defaultY = 48;

    public PanelItems(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(160, 528));

        setButtons();
        setUiImages();
    }

    private void setButtons() {
        endButton = new JButton("Save and exit");
        endButton.setBounds(defaultX, 450, 130, 60);
        endButton.setFocusable(false);
        endButton.addActionListener(this);
        this.add(endButton);

        pauseButton = new JButton("Pause");
        pauseButton.setBounds(defaultX, 405, 130, 30);
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(this);
        this.add(pauseButton);
    }

    public void setUiImages() {
        try {
            imagePotion = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
            imageKey = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            imageCoin = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(customFont);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(640, 0, 160, 528);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawImage(imageKey, defaultX, defaultY, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawString("x " + gamePanel.player.keys, defaultX + 36, defaultY + 20);

        graphics2D.drawImage(imageCoin, defaultX, defaultY + 40, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawString("x " + gamePanel.player.coins, defaultX + 36, defaultY + 60);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == endButton) {
            System.out.println("Ok");
        } else if (e.getSource() == pauseButton) {
            System.out.println("Pause");
        }
    }


    /*
    public boolean notify = false;
    String notification = null;
    Font customFont = new Font(null, Font.PLAIN, 20);
    int displayTimeCount = 0;


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

     */

    /*
    public void setMessage(String message) {
        notify = true;
        notification = message;
    }
     */
}
