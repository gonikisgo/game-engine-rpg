package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.objects.stat1c.Coin;
import cz.cvut.fel.pjv.objects.stat1c.Key;
import cz.cvut.fel.pjv.objects.stat1c.Potion;
import cz.cvut.fel.pjv.objects.stat1c.weapon.BasicWeapon;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends BasicMob {
    public int level;
    // public ArrayList<> keys;
    public int keys;
    public BasicWeapon[] weapons;
    public Coin[] coins;
    public Potion[] potion;
    public static int screenX;
    public static int screenY;

    int collisionIndex;


    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        screenX = gamePanel.tileSize * 10;
        screenY = gamePanel.tileSize * 8;

        this.keyHandler = keyHandler;
        solidAreaX = 8;
        solidAreaY = 10;
        solidArea = new Rectangle(solidAreaX, solidAreaY, 16, 18);
        setPositions();
        setImages();

        keys = 0;
    }

    public void setPositions() {
        canMove = true;
        moveDirection = "standing";

        objectWorldX = (10 + gamePanel.readJsonInfo.dictionary.get("Player").get(0).getX()) * gamePanel.tileSize;
        objectWorldY = (8 + gamePanel.readJsonInfo.dictionary.get("Player").get(0).getY()) * gamePanel.tileSize;
        speed = 5;
    }

    public void setImages() {
        try {
            standing = ImageIO.read(getClass().getResourceAsStream("/48bit/standing.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/48bit/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/48bit/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/48bit/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/48bit/left2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/48bit/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/48bit/down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/48bit/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/48bit/right2.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        moveDirection = "standing";

        if (keyHandler.upPressed == true) {
            moveDirection = "up";
        } else if (keyHandler.leftPressed == true) {
            moveDirection = "left";
        } else if (keyHandler.downPressed == true) {
            moveDirection = "down";
        } else if (keyHandler.rightPressed == true) {
            moveDirection = "right";
        }

        collision = false;
        // gamePanel.stateHandler.checker(this, this);
        // System.out.println(collision);

        collisionIndex = gamePanel.stateHandler.checkObject(this, this, true);
        objectInteraction();


        if (collision == false) {
            switch (moveDirection) {
                case "up":
                    objectWorldY -= speed;
                    break;
                case "left":
                    objectWorldX -= speed;
                    break;
                case "down":
                    objectWorldY += speed;
                    break;
                case "right":
                    objectWorldX += speed;
                    break;
            }
        }
    }

    public void objectInteraction() {
        if (collisionIndex != -1) {
            switch (gamePanel.allObjects.get(collisionIndex).name) {
                case "Key":
                    keys++;
                    gamePanel.allObjects.remove(collisionIndex);
                    break;
                case "Door":
                    if (keys > 0) {
                        gamePanel.allObjects.remove(collisionIndex);
                        keys--;
                    }
                    break;
                case "Potion":
                    speed += 5;
                    gamePanel.allObjects.remove(collisionIndex);
                    break;
                default:
                    gamePanel.allObjects.remove(collisionIndex);
                    break;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage actualImage = standing;

        if (keyHandler.upPressed || keyHandler.leftPressed || keyHandler.downPressed || keyHandler.rightPressed) {

            switch (moveDirection) {
                case "up":
                    actualImage = spriteChange == false ? up1 : up2;
                    break;
                case "left":
                    actualImage = spriteChange == false ? left1 : left2;
                    break;
                case "down":
                    actualImage = spriteChange == false ? down1 : down2;
                    break;
                case "right":
                    actualImage = spriteChange == false ? right1 : right2;
                    break;
            }

            spriteChangeRate++;
            if (spriteChangeRate > 5) {
                spriteChange = spriteChange == false ? true : false;
                spriteChangeRate = 0;
            }

        }

        graphics2D.drawImage(actualImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
