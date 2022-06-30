package cz.cvut.fel.pjv.utils;

import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.screen.GamePanel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
class to convert game objects' info and prepare for writing to .json file
 */
public class StateConvert {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public final static String[] names = new String[]{"Tree", "Water", "Key", "Potion", "Door", "Coin", "Player", "Monster", "Elf", "Biom", "LevelType", "PlayerSpeed", "PlayerKey", "PlayerPotion", "PlayerCoin", "PlayerWeapon", "Progress"};
    public Hashtable<String, ArrayList<ObjectInfo>> outputDict = new Hashtable<>();
    GamePanel gamePanel;

    public StateConvert(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void convertAllState() {
        for (int j = 0; j < 17; j++) {
            outputDict.put(names[j], new ArrayList<>());
        }

        String tmpName;
        for (int i = 0; i < gamePanel.allObjects.size(); i++) {
            tmpName = gamePanel.allObjects.get(i).getName();
            if (tmpName.equals("Tree")) {
                convertObjectCoordinates("Tree", i);
            } else if (tmpName.equals("Water")) {
                convertObjectCoordinates("Water", i);
            } else if (tmpName.equals("Key")) {
                convertObjectCoordinates("Key", i);
            } else if (tmpName.equals("Potion")) {
                convertObjectCoordinates("Potion", i);
            } else if (tmpName.equals("Door")) {
                convertObjectCoordinates("Door", i);
            } else if (tmpName.equals("Coin")) {
                convertObjectCoordinates("Coin", i);
            }
        }

        for (int i = 0; i < gamePanel.allMobs.size(); i++) {
            tmpName = gamePanel.allMobs.get(i).getName();
            if (tmpName.equals("Elf")) {
                convertMobCoordinates("Elf", i);
            } else if (tmpName.equals("Monster")) {
                convertMobCoordinatesData("Monster", "Monster", i);
            } else if (tmpName.equals("BossMonster")) {
                convertMobCoordinatesData("Monster", "BossMonster", i);
            }
        }
        convertPlayer();

        outputDict.get("Biom").add(new ObjectInfo(gamePanel.basicLevel.biom));

        String levelType = gamePanel.basicLevel.isBossLevel ? "Boss" : "NoBoss";
        outputDict.get("LevelType").add(new ObjectInfo(levelType));

        outputDict.get("Progress").add(new ObjectInfo(gamePanel.totalDamageOnMonster, gamePanel.levelHealth));
        LOGGER.log(Level.INFO, "state of game objects has been converted");
    }

    private void convertObjectCoordinates(String name, int index) {
        int x = (gamePanel.allObjects.get(index).getX() / GamePanel.tileSize) - 10;
        int y = (gamePanel.allObjects.get(index).getY() / GamePanel.tileSize) - 8;
        outputDict.get(name).add(new ObjectInfo(x, y));
    }

    private void convertMobCoordinates(String name, int index) {
        int x = gamePanel.allMobs.get(index).defaultWorldX - 10;
        int y = gamePanel.allMobs.get(index).defaultWorldY - 8;
        outputDict.get(name).add(new ObjectInfo(x, y));
    }

    private void convertMobCoordinatesData(String name, String options, int index) {
        int x = gamePanel.allMobs.get(index).defaultWorldX - 10;
        int y = gamePanel.allMobs.get(index).defaultWorldY - 8;
        int health = gamePanel.allMobs.get(index).getHealth();
        outputDict.get(name).add(new ObjectInfo(x, y, health, options));
    }

    private void convertPlayer() {
        int x = gamePanel.player.defaultWorldX - 10;
        int y = gamePanel.player.defaultWorldY - 8;
        int health = gamePanel.player.health;
        outputDict.get("Player").add(new ObjectInfo(x, y, health));
        convertPlayerObjects();
    }

    private void convertPlayerObjects() {
        outputDict.get("PlayerSpeed").add(new ObjectInfo(gamePanel.player.speed));
        outputDict.get("PlayerKey").add(new ObjectInfo(gamePanel.player.keys));
        outputDict.get("PlayerPotion").add(new ObjectInfo(gamePanel.player.potionDrunk));
        outputDict.get("PlayerCoin").add(new ObjectInfo(gamePanel.player.coins));

        int isSword = gamePanel.player.weapon[0] != null ? 1 : -1;
        int isBigSword = gamePanel.player.weapon[1] != null ? 1 : -1;
        outputDict.get("PlayerWeapon").add(new ObjectInfo(isSword, isBigSword));
    }
}
