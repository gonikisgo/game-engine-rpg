package cz.cvut.fel.pjv.objects;

import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static cz.cvut.fel.pjv.screen.GamePanel.tileSize;

public class BasicObject {
    public int objectWorldX, objectWorldY;
    public BufferedImage image;
    public String name;

    public boolean collision = false;
    public int solidAreaX = 0;
    public int solidAreaY = 0;

    public int solidAreaWidthX = 32;
    public int solidAreaHeightY = 32;

    public Rectangle solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);

    public String getName() {
        return name;
    }

    public int getX() {
        return objectWorldX;
    }

    public int getY() {
        return objectWorldY;
    }

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        int screenX = objectWorldX - gamePanel.player.objectWorldX + gamePanel.player.screenX;
        int screenY = objectWorldY - gamePanel.player.objectWorldY + gamePanel.player.screenY;

        if (objectWorldX + tileSize > gamePanel.player.objectWorldX - gamePanel.player.screenX &&
                objectWorldX - tileSize < gamePanel.player.objectWorldX + gamePanel.player.screenX &&
                objectWorldY + tileSize > gamePanel.player.objectWorldY - (gamePanel.player.screenY - 48) &&
                objectWorldY - tileSize < gamePanel.player.objectWorldY + (528 - gamePanel.player.screenY)) {
            graphics2D.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }
}
