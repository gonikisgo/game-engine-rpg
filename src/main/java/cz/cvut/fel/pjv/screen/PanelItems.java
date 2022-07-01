package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.WeaponHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
UI panel for showing player's objects and state
 */

public class PanelItems extends JPanel {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    private final Color customPurple = new Color(137, 160, 211);
    BufferedImage imageKey, imageCoin, imagePotion, sword, bigSword, inventory1, inventory2, inventory3, inventory4;
    GamePanel gamePanel;
    int defaultX = 640;
    int defaultY = 48;

    public PanelItems(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(160, 528));
        setUiImages();
    }

    public void setUiImages() {
        try {
            imageKey = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            imageCoin = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
            imagePotion = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
            sword = ImageIO.read(getClass().getResourceAsStream("/objects/sword.png"));
            bigSword = ImageIO.read(getClass().getResourceAsStream("/objects/big_sword.png"));

            inventory1 = ImageIO.read(getClass().getResourceAsStream("/objects/inventory1.png"));
            inventory2 = ImageIO.read(getClass().getResourceAsStream("/objects/inventory2.png"));
            inventory3 = ImageIO.read(getClass().getResourceAsStream("/objects/inventory3.png"));
            inventory4 = ImageIO.read(getClass().getResourceAsStream("/objects/inventory4.png"));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "inventory images weren't loaded", ex);
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(defaultX, 0, 160, 528);

        if (gamePanel.keyHandler.weaponChoice) {
            if (gamePanel.keyHandler.isMenuBigSword) {
                graphics2D.drawImage(inventory4, defaultX, defaultY, null);
            } else {
                graphics2D.drawImage(inventory2, defaultX, defaultY, null);
            }
        } else {
            if (gamePanel.keyHandler.isMenuBigSword) {
                graphics2D.drawImage(inventory3, defaultX, defaultY, null);
            } else {
                graphics2D.drawImage(inventory1, defaultX, defaultY, null);
            }
        }

        drawState(graphics2D);
        drawInventory(graphics2D);
    }

    private void drawState(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font(null, Font.PLAIN, 20));
        if (gamePanel.basicLevel.isBossLevel) {
            graphics2D.drawString("BOSS LEVEL", 665, 78);
        } else {
            graphics2D.setFont(new Font(null, Font.PLAIN, 18));
            graphics2D.drawString("NO BOSS LEVEL", 653, 78);
        }

        if (gamePanel.keyHandler.pausePressed) {
            graphics2D.setColor(Color.RED);
            graphics2D.drawString("PAUSED", 683, 105);
        } else {
            graphics2D.setColor(customPurple);
            graphics2D.drawRoundRect(655, 95, 130, 10, 2, 2);
            int progress = gamePanel.totalDamageOnMonster * 130 / gamePanel.levelHealth;
            graphics2D.fillRoundRect(655, 95, progress, 10, 2, 2);
        }

        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font(null, Font.PLAIN, 13));
        graphics2D.drawString("QUIT AND SAVE - Y", 659, 502);
    }

    private void drawInventory(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);

        graphics2D.setFont(new Font(null, Font.PLAIN, 13));
        graphics2D.drawString("INVENTORY", 650, 155);

        if (gamePanel.player.keys > 0) {
            graphics2D.drawImage(imageKey, 654, 175, null);
            graphics2D.drawString(Integer.toString(gamePanel.player.keys), 678, 212);
        }

        if (gamePanel.player.coins > 0) {
            graphics2D.drawImage(imageCoin, 697, 175, null);
            graphics2D.drawString(Integer.toString(gamePanel.player.coins), 726, 212);
        }

        if (gamePanel.player.potionDrunk > 0) {
            graphics2D.drawImage(imagePotion, 747, 175, null);
            graphics2D.drawString(Integer.toString(gamePanel.player.potionDrunk), 772, 212);
        }


        if (gamePanel.player.weapon[0] != null) {
            graphics2D.drawImage(sword, 669, 303, null);
        } else {
            graphics2D.setFont(new Font(null, Font.PLAIN, 10));
            graphics2D.drawImage(sword, 669, 375, null);
            graphics2D.drawString("5", 679, 433);
            graphics2D.drawImage(imageCoin, 684, 422, 16, 16, null);
            graphics2D.setFont(new Font(null, Font.PLAIN, 13));
            graphics2D.drawString("BUY", 678, 459);
        }

        if (gamePanel.player.weapon[1] != null) {
            graphics2D.drawImage(bigSword, 732, 303, null);
        } else {
            graphics2D.setFont(new Font(null, Font.PLAIN, 10));
            graphics2D.drawImage(bigSword, 732, 375, null);
            graphics2D.drawString("25", 740, 433);
            graphics2D.drawImage(imageCoin, 752, 422, 16, 16, null);
            graphics2D.setFont(new Font(null, Font.PLAIN, 13));
            graphics2D.drawString("BUY", 739, 459);
        }
    }
}
