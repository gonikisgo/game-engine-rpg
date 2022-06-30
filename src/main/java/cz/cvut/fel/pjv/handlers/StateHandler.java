package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

/*
class for checking collisions between objects
 */
public class StateHandler {
    GamePanel gamePanel;
    public StateHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void worldCollisionCheck(BasicMob basicMob, int upBorder, int leftBorder, int downBorder, int rightBorder) {
        switch (basicMob.moveDirection) {
            case "up":
                if ((basicMob.objectWorldY - basicMob.speed + basicMob.solidArea.y) < GamePanel.tileSize * upBorder) {
                    basicMob.collision = true;
                }
                break;
            case "left":
                if ((basicMob.objectWorldX - basicMob.speed + basicMob.solidArea.x) < GamePanel.tileSize * leftBorder) {
                    basicMob.collision = true;
                }
                break;
            case "down":
                if ((basicMob.objectWorldY + basicMob.speed) > GamePanel.tileSize * downBorder) {
                    basicMob.collision = true;
                }
                break;
            case "right":
                if ((basicMob.objectWorldX + basicMob.speed - basicMob.solidArea.x) > GamePanel.tileSize * rightBorder) {
                    basicMob.collision = true;
                }
                break;
        }
    }

    public int checkerObjects(BasicMob basicMob) {
        int collisionIndex = -1;
        for (int i = 0; i < gamePanel.allObjects.size(); i++) {
            basicMob.solidArea.x = basicMob.objectWorldX + basicMob.solidArea.x;
            basicMob.solidArea.y = basicMob.objectWorldY + basicMob.solidArea.y;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).objectWorldX + gamePanel.allObjects.get(i).solidArea.x;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).objectWorldY + gamePanel.allObjects.get(i).solidArea.y;

            directionCheck(basicMob);
            collisionIndex = checkIntersects(basicMob, i, collisionIndex);

            basicMob.solidArea.x = basicMob.solidAreaX;
            basicMob.solidArea.y = basicMob.solidAreaY;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).solidAreaX;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).solidAreaY;
        }
        if(collisionIndex == -2) {
            System.out.println(gamePanel.allObjects.get(collisionIndex).name);
            System.out.println(gamePanel.allObjects.get(collisionIndex).objectWorldX);
            System.out.println(gamePanel.allObjects.get(collisionIndex).objectWorldY);
        }
        return collisionIndex;
    }

    public int checkerMobs(BasicMob basicMob, int index) {
        int collisionIndex = -1;
        for (int i = 0; i < gamePanel.allMobs.size(); i++) {
            if (i != index) {
                basicMob.solidArea.x = basicMob.objectWorldX + basicMob.solidArea.x;
                basicMob.solidArea.y = basicMob.objectWorldY + basicMob.solidArea.y;

                gamePanel.allMobs.get(i).solidArea.x = gamePanel.allMobs.get(i).objectWorldX + gamePanel.allMobs.get(i).solidArea.x;
                gamePanel.allMobs.get(i).solidArea.y = gamePanel.allMobs.get(i).objectWorldY + gamePanel.allMobs.get(i).solidArea.y;

                directionCheck(basicMob);
                if (basicMob.solidArea.intersects(gamePanel.allMobs.get(i).solidArea)) {
                    basicMob.collision = true;
                    collisionIndex = i;
                }
                basicMob.solidArea.x = basicMob.solidAreaX;
                basicMob.solidArea.y = basicMob.solidAreaY;

                gamePanel.allMobs.get(i).solidArea.x = gamePanel.allMobs.get(i).solidAreaX;
                gamePanel.allMobs.get(i).solidArea.y = gamePanel.allMobs.get(i).solidAreaY;
            }
        }
        return collisionIndex;
    }


    public void checkerPlayer(BasicMob basicMob) {
        basicMob.solidArea.x = basicMob.objectWorldX + basicMob.solidArea.x;
        basicMob.solidArea.y = basicMob.objectWorldY + basicMob.solidArea.y;

        gamePanel.player.solidArea.x = gamePanel.player.objectWorldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.objectWorldY + gamePanel.player.solidArea.y;

        directionCheck(basicMob);
        if (basicMob.solidArea.intersects(gamePanel.player.solidArea)) {
            basicMob.collision = true;
        }
        basicMob.solidArea.x = basicMob.solidAreaX;
        basicMob.solidArea.y = basicMob.solidAreaY;

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaY;
    }

    public int checkerWeaponObjects(BasicObject basicObject) {
        int collisionIndex = -1;
        for (int i = 0; i < gamePanel.allObjects.size(); i++) {
            basicObject.solidArea.x = basicObject.objectWorldX;
            basicObject.solidArea.y = basicObject.objectWorldY;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).objectWorldX + gamePanel.allObjects.get(i).solidArea.x;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).objectWorldY + gamePanel.allObjects.get(i).solidArea.y;

            if (basicObject.solidArea.intersects(gamePanel.allObjects.get(i).solidArea)) {
                collisionIndex = i;
            }

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).solidAreaX;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).solidAreaY;
        }
        return collisionIndex;
    }

    public int checkerWeaponMobs(BasicObject basicObject) {
        int collisionIndex = -1;
        for (int i = 0; i < gamePanel.allMobs.size(); i++) {
            basicObject.solidArea.x = basicObject.objectWorldX;
            basicObject.solidArea.y = basicObject.objectWorldY;

            gamePanel.allMobs.get(i).solidArea.x = gamePanel.allMobs.get(i).objectWorldX + gamePanel.allMobs.get(i).solidArea.x;
            gamePanel.allMobs.get(i).solidArea.y = gamePanel.allMobs.get(i).objectWorldY + gamePanel.allMobs.get(i).solidArea.y;

            if (basicObject.solidArea.intersects(gamePanel.allMobs.get(i).solidArea)) {
                collisionIndex = i;
            }

            gamePanel.allMobs.get(i).solidArea.x = gamePanel.allMobs.get(i).solidAreaX;
            gamePanel.allMobs.get(i).solidArea.y = gamePanel.allMobs.get(i).solidAreaY;
        }
        return collisionIndex;
    }


    public boolean checkerMobsArea(int index) {
        boolean collisionIndex = false;

        gamePanel.allMobs.get(index).activeArea.x = gamePanel.allMobs.get(index).objectWorldX - gamePanel.allMobs.get(index).activeAreaSize;
        gamePanel.allMobs.get(index).activeArea.y = gamePanel.allMobs.get(index).objectWorldY - gamePanel.allMobs.get(index).activeAreaSize;

        gamePanel.player.solidArea.x = gamePanel.player.objectWorldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.objectWorldY + gamePanel.player.solidArea.y;

        if (gamePanel.player.solidArea.intersects(gamePanel.allMobs.get(index).activeArea)) {
            collisionIndex = true;
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaY;

        return collisionIndex;
    }

    private void directionCheck(BasicMob basicMob) {
        switch (basicMob.moveDirection) {
            case "up":
                basicMob.solidArea.y -= basicMob.speed;
                break;
            case "left":
                basicMob.solidArea.x -= basicMob.speed;
                break;
            case "down":
                basicMob.solidArea.y += basicMob.speed;
                break;
            case "right":
                basicMob.solidArea.x += basicMob.speed;
                break;
        }
    }

    private int checkIntersects(BasicObject basicObject, int i, int collisionIndex) {
        if (basicObject.solidArea.intersects(gamePanel.allObjects.get(i).solidArea)) {
            if (gamePanel.allObjects.get(i).collision == true) {
                basicObject.collision = true;
            }
            collisionIndex = i;
        }
        return collisionIndex;
    }
}
