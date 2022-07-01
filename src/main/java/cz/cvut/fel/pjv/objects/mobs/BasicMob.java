package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
base class for all mobs
 */
public class BasicMob extends BasicObject {
    final static int leftConstructorMapBorder = 10;
    final static int topConstructorMapBorder = 8;
    final static int rightConstructorMapBorder = 49;
    final static int bottomConstructorMapBorder = 37;
    public GamePanel gamePanel;
    public boolean canHeal = false;
    public boolean attacking = false;
    public int defaultWorldX, defaultWorldY;
    public int upBorder, leftBorder, downBorder, rightBorder;
    public int delta;
    public int activeAreaSize = GamePanel.tileSize / 2;
    // mob's active area is 16 pixels in every dimension
    public Rectangle activeArea = new Rectangle(0, 0, activeAreaSize * 2 + GamePanel.tileSize, activeAreaSize * 2 + GamePanel.tileSize);
    public String moveDirection = "standing";
    public int speed;
    public boolean spriteChange = false;
    public int spriteChangeRate = 0;
    public BufferedImage standing, up1, up2, left1, left2, down1, down2, right1, right2;
    String[] move = new String[]{"standing", "up", "left", "down", "right"};
    int frequency = 8; // to avoid frequent update
    int moveUpdateDefaultRate = 100;
    int moveUpdateRate = moveUpdateDefaultRate;

    public int getHealth() {
        return health;
    }

    public void setDefault(int x, int y) {
        defaultWorldX = x;
        defaultWorldY = y;
        objectWorldX = GamePanel.tileSize * x;
        objectWorldY = GamePanel.tileSize * y;
    }

    // setting moving square for mob
    public void setMoveArea() {
        upBorder = defaultWorldY - delta < topConstructorMapBorder ? topConstructorMapBorder : defaultWorldY - delta;
        leftBorder = defaultWorldX - delta < leftConstructorMapBorder ? leftConstructorMapBorder : defaultWorldX - delta;
        downBorder = defaultWorldY + delta > bottomConstructorMapBorder ? bottomConstructorMapBorder : defaultWorldY + delta;
        rightBorder = defaultWorldX + delta > rightConstructorMapBorder ? rightConstructorMapBorder : defaultWorldX + delta;
    }

    public void update(int index) {
        if (underAttack) {
            moveUpdateDefaultRate = 70;
            chooseMove(index);
        } else if (gamePanel.stateHandler.checkMobsActArea(index)) {
            moveDirection = move[0];

            if (frequency == 8) {
                switch (this.name) {
                    case "Elf":
                        if (canHeal) {
                            gamePanel.player.health += 5; // elf heals +3 xp each time
                        }
                        break;
                    case "Monster":
                        gamePanel.player.health -= 5; // monster damages -5 xp each time
                        break;
                    case "BossMonster":
                        gamePanel.player.health -= 20; // boss monster damages -5 xp each time
                        break;
                }
                frequency = 0;
            } else {
                frequency++;
            }
        } else {
            chooseMove(index);
        }
        updateImage();
    }

    private void chooseMove(int index) {
        int tryNum = 0;
        checkCollision(index);
        if (moveUpdateRate >= moveUpdateDefaultRate || collision) {
            collision = true;
            while (collision) {
                collision = false;
                if (underAttack) {
                    if (tryNum != 10) {
                        moveDirection = move[gamePanel.rand.nextInt(1, 5)];
                        tryNum++;
                    } else {
                        moveDirection = move[0];
                    }
                } else {
                    moveDirection = move[gamePanel.rand.nextInt(5)];
                }
                checkCollision(index);
            }
            moveUpdateRate = 0;
        } else {
            moveUpdateRate++;
        }
        speedChange(this);
    }

    private void checkCollision(int index) {
        gamePanel.stateHandler.checkWorldCollision(this, upBorder, leftBorder, downBorder, rightBorder);
        gamePanel.stateHandler.checkCollWithObjects(this);
        gamePanel.stateHandler.checkCollWithMobs(this, index);
        gamePanel.stateHandler.checkCollWithPlayer(this);
    }

    public void speedChange(BasicMob basicMob) {
        switch (basicMob.moveDirection) {
            case "up":
                basicMob.objectWorldY -= basicMob.speed;
                break;
            case "left":
                basicMob.objectWorldX -= basicMob.speed;
                break;
            case "down":
                basicMob.objectWorldY += basicMob.speed;
                break;
            case "right":
                basicMob.objectWorldX += basicMob.speed;
                break;
        }
    }

    public void updateImage() {
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

        if (spriteChangeRate > 4) {
            spriteChange = spriteChange == false;
            spriteChangeRate = 0;
        } else {
            spriteChangeRate++;
        }
    }
}
