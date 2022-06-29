package cz.cvut.fel.pjv.handlers;

import com.sun.source.tree.BreakTree;
import cz.cvut.fel.pjv.levels.BasicLevel;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

public class StateHandler {
    GamePanel gamePanel;
    public StateHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checker(BasicObject basicObject, BasicMob basicMob) {

        int objectWorldLeftX = basicObject.objectWorldX + 6;
        int objectWorldRightX = basicObject.objectWorldX + 6 + 20;
        int objectWorldUpY = basicObject.objectWorldY + 3;
        int objectWorldDownY = basicObject.objectWorldY + 3 + 25;

        int objectColLeft = objectWorldLeftX / gamePanel.tileSize;
        int objectColRight = objectWorldRightX / gamePanel.tileSize;
        int objectRowUp = objectWorldUpY / gamePanel.tileSize;
        int objectRowDown = objectWorldDownY / gamePanel.tileSize;

        switch (basicObject.moveDirection) {
            case "up":
                objectRowUp = (objectWorldUpY - basicMob.speed) / gamePanel.tileSize;
                if (gamePanel.basicLevel.tiles[objectColLeft][objectRowUp].collision == true || gamePanel.basicLevel.tiles[objectColRight][objectRowUp].collision == true) {
                    basicObject.collision = true;
                }
                break;
            case "left":
                objectColLeft = (objectWorldLeftX - basicMob.speed) / gamePanel.tileSize;
                if (gamePanel.basicLevel.tiles[objectColLeft][objectRowDown].collision == true || gamePanel.basicLevel.tiles[objectColLeft][objectRowUp].collision == true) {
                    basicObject.collision = true;
                }
                break;
            case "down":
                objectRowDown = (objectWorldDownY + basicMob.speed) / gamePanel.tileSize;
                if (gamePanel.basicLevel.tiles[objectColLeft][objectRowDown].collision == true || gamePanel.basicLevel.tiles[objectColRight][objectRowDown].collision == true) {
                    basicObject.collision = true;
                }
                break;
            case "right":
                objectColRight = (objectWorldRightX + basicMob.speed) / gamePanel.tileSize;
                if (gamePanel.basicLevel.tiles[objectColRight][objectRowDown].collision == true || gamePanel.basicLevel.tiles[objectColRight][objectRowUp].collision == true) {
                    basicObject.collision = true;
                }
                break;
        }
    }

    public int checkObject(BasicObject basicObject, BasicMob basicMob, boolean isPlayer) {
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
