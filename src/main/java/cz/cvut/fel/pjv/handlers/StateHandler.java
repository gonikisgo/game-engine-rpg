package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

public class StateHandler {
    GamePanel gamePanel;
    public StateHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkerWorld(BasicObject basicObject, BasicMob basicMob) {
        switch (basicObject.moveDirection) {
            case "up":
                if ((basicObject.objectWorldY - basicMob.speed) < gamePanel.tileSize * 8) {
                    basicObject.collision = true;
                }
                break;
            case "left":
                if ((basicObject.objectWorldX - basicMob.speed) < gamePanel.tileSize * 10) {
                    basicObject.collision = true;
                }
                break;
            case "down":
                if ((basicObject.objectWorldY + basicMob.speed) > gamePanel.tileSize * 37) {
                    basicObject.collision = true;
                }
                break;
            case "right":
                if ((basicObject.objectWorldX + basicMob.speed) > gamePanel.tileSize * 49) {
                    basicObject.collision = true;
                }
                break;
        }
    }

    public int checkerObjects(BasicObject basicObject, BasicMob basicMob, boolean isPlayer) {
        int collisionIndex = -1;
        for (int i = 0; i < gamePanel.allObjects.size(); i++) {
            basicObject.solidArea.x = basicObject.objectWorldX + basicObject.solidArea.x;
            basicObject.solidArea.y = basicObject.objectWorldY + basicObject.solidArea.y;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).objectWorldX + gamePanel.allObjects.get(i).solidArea.x;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).objectWorldY + gamePanel.allObjects.get(i).solidArea.y;

            switch (basicObject.moveDirection) {
                case "up":
                    basicObject.solidArea.y -= basicMob.speed;
                    break;
                case "left":
                    basicObject.solidArea.x -= basicMob.speed;
                    break;
                case "down":
                    basicObject.solidArea.y += basicMob.speed;
                    break;
                case "right":
                    basicObject.solidArea.x += basicMob.speed;
                    break;
            }
            collisionIndex = checkObjectsConditions(basicObject, i, isPlayer, collisionIndex);

            basicObject.solidArea.x = basicObject.solidAreaX;
            basicObject.solidArea.y = basicObject.solidAreaY;

            gamePanel.allObjects.get(i).solidArea.x = gamePanel.allObjects.get(i).solidAreaX;
            gamePanel.allObjects.get(i).solidArea.y = gamePanel.allObjects.get(i).solidAreaY;
        }
        return collisionIndex;
    }

    int checkObjectsConditions(BasicObject basicObject, int i, boolean isPlayer, int collisionIndex) {
        if (basicObject.solidArea.intersects(gamePanel.allObjects.get(i).solidArea)) {
            if (gamePanel.allObjects.get(i).collision == true) {
                basicObject.collision = true;
            }
            if (isPlayer) {
                collisionIndex = i;
            }
        }
        return collisionIndex;
    }
}
