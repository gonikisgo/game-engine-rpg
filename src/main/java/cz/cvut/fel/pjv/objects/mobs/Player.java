package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.objects.stat1c.weapon.BasicWeapon;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends BasicMob {
    public int level;
    public int keys = 0;
    public int coins;
    public int potion;
    public int XP;
    public BasicWeapon[] weapons;
    public int screenX;
    public int screenY;

    int collisionIndex;


    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        health = 100;
        screenX = gamePanel.tileSize * 10;
        screenY = gamePanel.tileSize * 8 + 48;

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

        objectWorldX = (gamePanel.readJsonInfo.dictionary.get("Player").get(0).getX() + 10) * gamePanel.tileSize;
        objectWorldY = (gamePanel.readJsonInfo.dictionary.get("Player").get(0).getY() + 8) * gamePanel.tileSize;
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
        gamePanel.stateHandler.checkerWorld( this, 8, 10, 37, 49);

        if (collision == false) {
            collisionIndex = gamePanel.stateHandler.checkerObjects(this);
            if (collisionIndex != -1) {
                objectInteraction();
            }
        }
        if (collision == false) {
            collisionIndex = gamePanel.stateHandler.checkerMobs(this, -1);
            if (collisionIndex != -1) {
                mobInteraction();
            }
        }


        if (!collision) {
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
        updateImage();
    }

    private void objectInteraction() {
        switch (gamePanel.allObjects.get(collisionIndex).name) {
            case "Key":
                keys++;
                //gamePanel.gameUI.setMessage("+ key");
                gamePanel.allObjects.remove(collisionIndex);
                break;
            case "Door":
                if (keys > 0) {
                    gamePanel.allObjects.remove(collisionIndex);
                    keys--;
                }
                break;
            case "Potion":
                speed += 1;
                gamePanel.allObjects.remove(collisionIndex);
                break;
            case "Coin":
                coins++;
                gamePanel.allObjects.remove(collisionIndex);
                break;
        }
    }

    private void mobInteraction() {
        switch (gamePanel.allMobs.get(collisionIndex).name) {
            case "Monster":
                health -= 5;
                //gamePanel.gameUI.setMessage(" -5XP");
                break;
        }
    }

    private void updateImage() {
        image = standing;

        if (keyHandler.upPressed || keyHandler.leftPressed || keyHandler.downPressed || keyHandler.rightPressed) {

            switch (moveDirection) {
                case "up":
                    image = spriteChange == false ? up1 : up2;
                    break;
                case "left":
                    image = spriteChange == false ? left1 : left2;
                    break;
                case "down":
                    image = spriteChange == false ? down1 : down2;
                    break;
                case "right":
                    image = spriteChange == false ? right1 : right2;
                    break;
            }

            spriteChangeRate++;
            if (spriteChangeRate > 5) {
                spriteChange = spriteChange == false ? true : false;
                spriteChangeRate = 0;
            }

        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
