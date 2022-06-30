package cz.cvut.fel.pjv.objects;

import cz.cvut.fel.pjv.screen.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
base entity for all objects in the game
 */

public class BasicObject {
    public GamePanel gamePanel;
    public boolean canBeDestroyed = false;
    public boolean underAttack = false;
    public int objectWorldX, objectWorldY;
    public BufferedImage image;
    public String name;
    public int health = 20;
    public int damage;
    public boolean collision = false;
    public int solidAreaX, solidAreaY;
    public int solidAreaWidthX, solidAreaHeightY;
    public Rectangle solidArea;
    public int frameHeight = 528;
    public int downOffsetY = 48;

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
        if (health < 15) {
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        int screenX = objectWorldX - gamePanel.player.objectWorldX + gamePanel.player.screenX;
        int screenY = objectWorldY - gamePanel.player.objectWorldY + gamePanel.player.screenY;

        if (objectWorldX + GamePanel.tileSize > gamePanel.player.objectWorldX - gamePanel.player.screenX && objectWorldX - GamePanel.tileSize < gamePanel.player.objectWorldX + gamePanel.player.screenX && objectWorldY + GamePanel.tileSize > gamePanel.player.objectWorldY - (gamePanel.player.screenY - downOffsetY) && objectWorldY - GamePanel.tileSize < gamePanel.player.objectWorldY + (frameHeight - gamePanel.player.screenY)) {
            graphics2D.drawImage(image, screenX, screenY, null);
        }
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
