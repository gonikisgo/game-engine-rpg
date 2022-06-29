package cz.cvut.fel.pjv.utils;

import cz.cvut.fel.pjv.levels.LevelConstructor;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import java.util.ArrayList;
import java.util.Hashtable;

public class StateWrite {
    public Hashtable<String, ArrayList<LevelConstructor.ObjectInfo>> outputDict;
    ArrayList<BasicObject> allObjects;
    GamePanel gamePanel;
    public final static String[] names = new String[] {"Tree", "Water", "Key", "Potion", "Door", "Coin", "Player",
            "Monster", "Elf", "Biom", "LevelType"};

    public StateWrite(ArrayList<BasicObject> allObjects, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.allObjects = allObjects;

        outputDict = new Hashtable<String, ArrayList<LevelConstructor.ObjectInfo>>();

        for (int j = 0; j < 11; j++) {
            outputDict.put(names[j], new ArrayList<LevelConstructor.ObjectInfo>());
        }


        for (int i = 0; i < allObjects.size(); i++) {
            String tmpName = allObjects.get(i).getName();
            if (tmpName.equals("Tree")) {
                setCoordinates("Tree", i);
            } else if (tmpName.equals("Water")) {
                setCoordinates("Water", i);
            } else if (tmpName.equals("Key")) {
                setCoordinates("Key", i);
            } else if (tmpName.equals("Potion")) {
                setCoordinates("Potion", i);
            } else if (tmpName.equals("Door")) {
                setCoordinates("Door", i);
            } else if (tmpName.equals("Coin")) {
                setCoordinates("Coin", i);
            } else if (tmpName.equals("Monster")) {
                setCoordinates("Monster", i);
            } else if (tmpName.equals("Elf")) {
                setCoordinates("Elf", i);
            }
        }

        int x = (gamePanel.player.objectWorldX / gamePanel.tileSize) - 10;
        int y = (gamePanel.player.objectWorldY / gamePanel.tileSize) - 8;
        outputDict.get("Player").add(new LevelConstructor.ObjectInfo(x, y));

        outputDict.get("Biom").add(new LevelConstructor.ObjectInfo(gamePanel.basicLevel.biom));

        String levelType = gamePanel.basicLevel.isBossLevel? "Boss" : "NoBoss";
        outputDict.get("LevelType").add(new LevelConstructor.ObjectInfo(levelType));
    }

    private void setCoordinates(String name, int index) {
        int x = (allObjects.get(index).getX() / gamePanel.tileSize) - 10;
        int y = (allObjects.get(index).getY() / gamePanel.tileSize) - 8;
        outputDict.get(name).add(new LevelConstructor.ObjectInfo(x, y));
    }
}
