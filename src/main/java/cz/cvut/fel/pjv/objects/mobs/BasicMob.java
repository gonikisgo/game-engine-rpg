package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BasicMob extends BasicObject {
    public GamePanel gamePanel;

    public int defaultWorldX, defaultWorldY;

    public int upBorder, leftBorder, downBorder, rightBorder;

    public int delta = -1;
    public Rectangle Area;
    public String moveDirection = "Standing";
    public int speed;
    public boolean spriteChange = false;
    public int spriteChangeRate = 0;
    public int health;
    public boolean canMove;
    Random rand = new Random();
    public String[] move = new String[]{"standing", "up", "left", "down", "right"};
    int moveUpdateRate = 100;
    public BufferedImage standing, up1, up2, left1, left2, down1, down2, right1, right2;
    public boolean canDamage =  false;


    public int getHealth() {
        return health;
    }

    public void setDefault(int x, int y) {
        defaultWorldX = x;
        defaultWorldY = y;
        objectWorldX = gamePanel.tileSize * x;
        objectWorldY = gamePanel.tileSize * y;
    }

    public void setMoveArea() {
        upBorder = defaultWorldY - delta < 8 ? 8 : defaultWorldY - delta;
        leftBorder = defaultWorldX - delta < 10 ? 10 : defaultWorldX - delta;
        downBorder = defaultWorldY + delta > 37 ? 37 : defaultWorldY + delta;
        rightBorder = defaultWorldX + delta > 49 ? 49 : defaultWorldX + delta;
    }

    public void update(int index) {
        chooseMove(index);

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
        updateImage();
    }

    private void checkCollision(int index) {
        gamePanel.stateHandler.checkerWorld(this, upBorder, leftBorder, downBorder, rightBorder);
        gamePanel.stateHandler.checkerObjects(this);
        gamePanel.stateHandler.checkerMobs(this, index);
        gamePanel.stateHandler.checkerPlayer(this);
    }

    private void chooseMove(int index) {
        checkCollision(index);

        if (moveUpdateRate == 100 || collision) {
            collision = true;
            while (collision) {
                collision = false;
                moveDirection = move[rand.nextInt(5)];
                checkCollision(index);
            }
            moveUpdateRate = 0;
        }
        moveUpdateRate++;
    }

    private void updateImage() {
        image = standing;

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
