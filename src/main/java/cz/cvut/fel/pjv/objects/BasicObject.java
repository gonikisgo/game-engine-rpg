package cz.cvut.fel.pjv.objects;

import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static cz.cvut.fel.pjv.screen.GamePanel.tileSize;

public class BasicObject {
    public int objectWorldX, objectWorldY;
    public String moveDirection;
    public BufferedImage image;
    public String name;
    public String description;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public int solidAreaX = 0;
    public int solidAreaY = 0;


    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        int screenX = objectWorldX - gamePanel.player.objectWorldX + Player.screenX;
        int screenY = objectWorldY - gamePanel.player.objectWorldY + Player.screenY;
        // System.out.printf("x - %d, y - %d\n", screenX, screenY);

        if (objectWorldX + tileSize > gamePanel.player.objectWorldX - Player.screenX &&
                objectWorldX - tileSize < gamePanel.player.objectWorldX + Player.screenX &&
                objectWorldY + tileSize > gamePanel.player.objectWorldY - Player.screenY &&
                objectWorldY - tileSize < gamePanel.player.objectWorldY + Player.screenY) {
            graphics2D.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }
}
