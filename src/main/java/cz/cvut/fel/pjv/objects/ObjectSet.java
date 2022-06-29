package cz.cvut.fel.pjv.objects;

import cz.cvut.fel.pjv.main.Game;
import cz.cvut.fel.pjv.objects.stat1c.*;
import cz.cvut.fel.pjv.screen.GamePanel;

public class ObjectSet {
    GamePanel gamePanel;
    int coinsCount;
    int doorsCount;
    int keysCount;
    int potionCount;
    int treesCount;
    int waterCount;

    public ObjectSet(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        coinsCount = gamePanel.readJsonInfo.dictionary.get("Coin").size();
        doorsCount = gamePanel.readJsonInfo.dictionary.get("Door").size();
        keysCount = gamePanel.readJsonInfo.dictionary.get("Key").size();
        potionCount = gamePanel.readJsonInfo.dictionary.get("Potion").size();
        treesCount = gamePanel.readJsonInfo.dictionary.get("Tree").size();
        waterCount = gamePanel.readJsonInfo.dictionary.get("Water").size();
    }

    public void objectSetter() {
        // set objects to (x, y) tiles on the map
        int offset = 0;

        for (int i = 0; i < coinsCount; i++)
        {
            gamePanel.allObjects.add(new Coin());
            gamePanel.allObjects.get(offset).objectWorldX = gamePanel.tileSize * (10 + gamePanel.readJsonInfo.dictionary.get("Coin").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = gamePanel.tileSize * (8 + gamePanel.readJsonInfo.dictionary.get("Coin").get(i).getY());
            offset++;
        }

        for (int i = 0; i < doorsCount; i++)
        {
            gamePanel.allObjects.add(new Door());
            gamePanel.allObjects.get(offset).objectWorldX = gamePanel.tileSize * (10 + gamePanel.readJsonInfo.dictionary.get("Door").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = gamePanel.tileSize * (8 + gamePanel.readJsonInfo.dictionary.get("Door").get(i).getY());
            offset++;
        }

        for (int i = 0; i < keysCount; i++)
        {
            gamePanel.allObjects.add(new Key());
            gamePanel.allObjects.get(offset).objectWorldX = gamePanel.tileSize * (10 + gamePanel.readJsonInfo.dictionary.get("Key").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = gamePanel.tileSize * (8 + gamePanel.readJsonInfo.dictionary.get("Key").get(i).getY());
            offset++;
        }

        for (int i = 0; i < potionCount; i++)
        {
            gamePanel.allObjects.add(new Potion());
            gamePanel.allObjects.get(offset).objectWorldX = gamePanel.tileSize * (10 + gamePanel.readJsonInfo.dictionary.get("Potion").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = gamePanel.tileSize * (8 + gamePanel.readJsonInfo.dictionary.get("Potion").get(i).getY());
            offset++;
        }

        for (int i = 0; i < treesCount; i++)
        {
            gamePanel.allObjects.add(new Tree());
            gamePanel.allObjects.get(offset).objectWorldX = gamePanel.tileSize * (10 + gamePanel.readJsonInfo.dictionary.get("Tree").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = gamePanel.tileSize * (8 + gamePanel.readJsonInfo.dictionary.get("Tree").get(i).getY());
            offset++;
        }

        for (int i = 0; i < waterCount; i++)
        {
            gamePanel.allObjects.add(new Water());
            gamePanel.allObjects.get(offset).objectWorldX = gamePanel.tileSize * (10 + gamePanel.readJsonInfo.dictionary.get("Water").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = gamePanel.tileSize * (8 + gamePanel.readJsonInfo.dictionary.get("Water").get(i).getY());
            offset++;
        }

    }
}
