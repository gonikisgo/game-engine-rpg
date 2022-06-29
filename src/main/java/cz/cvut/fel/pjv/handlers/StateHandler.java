package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

public class StateHandler {
    GamePanel gamePanel;
    public StateHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkerWorld(BasicMob basicMob, int upBorder, int leftBorder, int downBorder, int rightBorder) {
        switch (basicMob.moveDirection) {
            case "up":
                if ((basicMob.objectWorldY - basicMob.speed + basicMob.solidArea.y) < gamePanel.tileSize * upBorder) {
                    basicMob.collision = true;
                }
                break;
            case "left":
                if ((basicMob.objectWorldX - basicMob.speed + basicMob.solidArea.x) < gamePanel.tileSize * leftBorder) {
                    basicMob.collision = true;
                }
                break;
            case "down":
                if ((basicMob.objectWorldY + basicMob.speed) > gamePanel.tileSize * downBorder) {
                    basicMob.collision = true;
                }
                break;
            case "right":
                if ((basicMob.objectWorldX + basicMob.speed - basicMob.solidArea.x) > gamePanel.tileSize * rightBorder) {
                    basicMob.collision = true;
                }
                break;
        }
    }

    public void directionCheck(BasicMob basicMob) {
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

    public int checkerObjects(BasicMob basicMob) {
        int collisionIndex = -1;
        for (int i = 0; i < gamePanel.allObjects.size(); i++) {
            basicMob.solidArea.x = basicMob.objectWorldX + basicMob.solidArea.x;
            basicMob.solidArea.y = basicMob.objectWorldY + basicMob.solidArea.y;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).objectWorldX + gamePanel.allObjects.get(i).solidArea.x;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).objectWorldY + gamePanel.allObjects.get(i).solidArea.y;

            directionCheck(basicMob);
            collisionIndex = checkObjectsConditions(basicMob, i, collisionIndex);

            basicMob.solidArea.x = basicMob.solidAreaX;
            basicMob.solidArea.y = basicMob.solidAreaY;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).solidAreaX;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).solidAreaY;
        }
        return collisionIndex;
    }

    int checkObjectsConditions(BasicObject basicObject, int i, int collisionIndex) {
        if (basicObject.solidArea.intersects(gamePanel.allObjects.get(i).solidArea)) {
            if (gamePanel.allObjects.get(i).collision == true) {
                basicObject.collision = true;
            }
            collisionIndex = i;
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
}
