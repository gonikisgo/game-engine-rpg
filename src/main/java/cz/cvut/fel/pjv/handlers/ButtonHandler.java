package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.screen.LevelConstructor;
import cz.cvut.fel.pjv.utils.ObjectInfo;
import cz.cvut.fel.pjv.utils.WriteJson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
class for listening buttons in level constructor
 */

public class ButtonHandler implements ActionListener {
    LevelConstructor levelConstructor;

    public ButtonHandler(LevelConstructor levelConstructor) {
        this.levelConstructor = levelConstructor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        levelConstructor.pressedButtonNum = -1;
        if (e.getSource() == levelConstructor.buttonTree) {
            levelConstructor.pressedButtonNum = 0;
        } else if (e.getSource() == levelConstructor.buttonWater) {
            levelConstructor.pressedButtonNum = 1;
        } else if (e.getSource() == levelConstructor.buttonKey) {
            levelConstructor.pressedButtonNum = 2;
        } else if (e.getSource() == levelConstructor.buttonPotion) {
            levelConstructor.pressedButtonNum = 3;
        } else if (e.getSource() == levelConstructor.buttonDoor) {
            levelConstructor.pressedButtonNum = 4;
        } else if (e.getSource() == levelConstructor.buttonCoin) {
            levelConstructor.pressedButtonNum = 5;
        } else if (e.getSource() == levelConstructor.buttonPlayer) {
            levelConstructor.pressedButtonNum = 6;
        } else if (e.getSource() == levelConstructor.buttonMonster) {
            levelConstructor.pressedButtonNum = 7;
        } else if (e.getSource() == levelConstructor.buttonElf) {
            levelConstructor.pressedButtonNum = 8;
        } else if (e.getSource() == levelConstructor.buttonApply) {
            if (!levelConstructor.isBiom) {
                new LevelConstructor.Alert("To start game - you should choose biom!");
            } else if (!levelConstructor.isBoss) {
                new LevelConstructor.Alert("To start game - you should choose level type!");
            } else {
                for (int j = 0; j < LevelConstructor.names.length - 1; j++) {
                    levelConstructor.objectsDict.put(LevelConstructor.names[j], new ArrayList<>());
                }
                for (int x = 0; x < levelConstructor.gridWidth; x++) {
                    for (int y = 0; y < levelConstructor.gridHeight; y++) {
                        for (int i = 0; i < 9; i++) {
                            if (levelConstructor.cells[x][y].getBackground() == levelConstructor.customColors[i]) {
                                levelConstructor.objectsDict.get(LevelConstructor.names[i]).add(new ObjectInfo(x, y));
                                break;
                            }
                        }
                    }
                }
                levelConstructor.objectsDict.get(LevelConstructor.names[9]).add(new ObjectInfo(levelConstructor.biomChoice));
                levelConstructor.objectsDict.get(LevelConstructor.names[10]).add(new ObjectInfo(levelConstructor.bossChoice));

                if (levelConstructor.objectsDict.get("Player").size() > 1) {
                    new LevelConstructor.Alert("You can locate only one player on the map");
                } else if (levelConstructor.objectsDict.get("Player").size() == 0) {
                    new LevelConstructor.Alert("To start game - you should locate player on the map");
                } else {
                    new WriteJson(levelConstructor.objectsDict);
                    levelConstructor.constructorWindow.dispose();
                    levelConstructor.menu.gameUI.loadGame();
                }
            }

        } else if (e.getSource() == levelConstructor.snowChoiceButton) {
            levelConstructor.isBiom = true;
            levelConstructor.biomChoice = "Snow";
        } else if (e.getSource() == levelConstructor.forestChoiceButton) {
            levelConstructor.isBiom = true;
            levelConstructor.biomChoice = "Forest";
        } else if (e.getSource() == levelConstructor.yesBossButton) {
            levelConstructor.isBoss = true;
            levelConstructor.bossChoice = "Boss";
        } else if (e.getSource() == levelConstructor.noBossButton) {
            levelConstructor.isBoss = true;
            levelConstructor.bossChoice = "NoBoss";
        }
    }
}
