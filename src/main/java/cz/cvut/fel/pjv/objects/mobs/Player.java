package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.objects.stat1c.weapon.BasicWeapon;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
player class
 */

public class Player extends BasicMob {
    public int keys = 0;
    public int potionDrunk = 0;
    public int coins = 0;
    public int screenX;
    public int screenY;
    public int collisionIndex;
    public int frequency = 4; // to avoid frequent updates
    public BasicWeapon[] weapon = new BasicWeapon[2];
    public BasicWeapon currentWeapon = null;
    int actualScreenX, actualScreenY;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        health = 100;
        screenX = GamePanel.tileSize * 10; // middle of the x on panel
        screenY = GamePanel.tileSize * 8 + downOffsetY; // middle of the y on panel
        solidAreaX = 7;
        solidAreaY = 5;
        solidAreaWidthX = 18;
        solidAreaHeightY = 22;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);

        // offset player's world borders, because constructor map is smaller than actual game map
        upBorder = 8;
        leftBorder = 10;
        downBorder = 37;
        rightBorder = 49;

        moveDirection = "standing";
        speed = 5;
        setImages();
    }

    private void setImages() {
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/right2.png"));
                    break;
                case "Snow":
                    standing = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/standing.png"));
                    up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/up1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/up2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/left1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/left2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/down1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/down2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/right1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/right2.png"));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        attacking = false;
        actualScreenX = screenX;
        actualScreenY = screenY;
        moveDirection = "standing";

        gamePanel.weaponHandler.weaponBuyCheck();
        if (weapon[0] != null || weapon[1] != null) {
            gamePanel.weaponHandler.weaponChoice();
        }

        if (keyHandler.upPressed) {
            moveDirection = "up";
        } else if (keyHandler.leftPressed) {
            moveDirection = "left";
        } else if (keyHandler.downPressed) {
            moveDirection = "down";
        } else if (keyHandler.rightPressed) {
            moveDirection = "right";
        } else if (keyHandler.attackUpPressed) {
            moveDirection = "upAttack";
            attacking = true;
        } else if (keyHandler.attackLeftPressed) {
            moveDirection = "leftAttack";
            attacking = true;
        } else if (keyHandler.attackDownPressed) {
            moveDirection = "downAttack";
            attacking = true;
        } else if (keyHandler.attackRightPressed) {
            moveDirection = "rightAttack";
            attacking = true;
        }

        collision = false;
        if (attacking && currentWeapon != null) {
            attackSet();
            collisionIndex = gamePanel.stateHandler.checkerWeaponObjects(currentWeapon);
            if (collisionIndex != -1) {
                gamePanel.objectHandler.weaponObjectInteraction();
            }
            collisionIndex = gamePanel.stateHandler.checkerWeaponMobs(currentWeapon);
            if (collisionIndex != -1) {
                gamePanel.objectHandler.weaponMobInteraction();
            }
        } else {
            gamePanel.stateHandler.worldCollisionCheck(this, upBorder, leftBorder, downBorder, rightBorder);
            if (collision == false) {
                collisionIndex = gamePanel.stateHandler.checkerObjects(this);
                if (collisionIndex != -1) {
                    gamePanel.objectHandler.playerObjectInteraction();
                }
            }
            if (collision == false) {
                collisionIndex = gamePanel.stateHandler.checkerMobs(this, -1);
                if (collisionIndex != -1) {
                    gamePanel.objectHandler.playerMobInteraction();
                }
            }
            if (!collision) {
                speedChange(this);
            }
        }
        updatePlayerImage();
    }

    private void attackSet() {
        // setting sword's actual solid area
        switch (moveDirection) {
            case "upAttack":
                currentWeapon.objectWorldX = this.objectWorldX + currentWeapon.solidAreaY;
                currentWeapon.objectWorldY = this.objectWorldY + currentWeapon.playerHandOffset - currentWeapon.solidAreaWidthX;
                actualScreenY -= 18; // player with sword tile is 50*32 pixels
                break;
            case "leftAttack":
                currentWeapon.objectWorldX = this.objectWorldX + currentWeapon.playerHandOffset - currentWeapon.solidAreaWidthX;
                currentWeapon.objectWorldY = this.objectWorldY + currentWeapon.solidAreaY;
                actualScreenX -= 18; // player with sword tile is 50*32 pixels
                break;
            case "downAttack":
                currentWeapon.objectWorldX = this.objectWorldX + (GamePanel.tileSize - currentWeapon.solidAreaHeightY - currentWeapon.solidAreaY);
                currentWeapon.objectWorldY = this.objectWorldY + (GamePanel.tileSize - currentWeapon.playerHandOffset);
                break;
            case "rightAttack":
                currentWeapon.objectWorldX = this.objectWorldX + (GamePanel.tileSize - currentWeapon.playerHandOffset);
                currentWeapon.objectWorldY = this.objectWorldY + currentWeapon.solidAreaY;
                break;
        }
    }

    private void updatePlayerImage() {
        image = standing;
        if (!moveDirection.equals("standing")) {
            if (attacking && currentWeapon != null) {
                switch (moveDirection) {
                    case "upAttack":
                        image = currentWeapon.upAttack;
                        break;
                    case "leftAttack":
                        image = currentWeapon.leftAttack;
                        break;
                    case "downAttack":
                        image = currentWeapon.downAttack;
                        break;
                    case "rightAttack":
                        image = currentWeapon.rightAttack;
                        break;
                }
            } else {
                updateImage();
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, actualScreenX, actualScreenY, null);
    }
}
