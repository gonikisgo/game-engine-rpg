package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.screen.LevelConstructor;
import cz.cvut.fel.pjv.utils.ObjectInfo;
import cz.cvut.fel.pjv.utils.WriteJson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * class for listening buttons in level constructor window
 * @author kiselnik
 */

public class ButtonHandler implements ActionListener {
    private final static Logger LOGGER = Logger.getLogger(ButtonHandler.class.getName());
    Handler consoleHandler = new ConsoleHandler();
    LevelConstructor levelConstructor;

    public ButtonHandler(LevelConstructor levelConstructor) {
        this.levelConstructor = levelConstructor;
        LOGGER.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.CONFIG);
        LOGGER.setLevel(Level.CONFIG);
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
                // checking exception
                new LevelConstructor.Alert("To start game - you should choose biom!");
            } else if (!levelConstructor.isBoss) {
                // checking second exception
                new LevelConstructor.Alert("To start game - you should choose level type!");
            } else {
                // creating dictionary with all chosen objects in level constructor
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
                    // checking if there is only one player located on the map
                    new LevelConstructor.Alert("You can locate only one player on the map");
                } else if (levelConstructor.objectsDict.get("Player").size() == 0) {
                    // checking if player located on the map
                    new LevelConstructor.Alert("To start game - you should locate player on the map");
                } else {
                    new WriteJson(levelConstructor.objectsDict);
                    levelConstructor.constructorWindow.dispose();
                    levelConstructor.menu.gameUI.loadGame();
                    LOGGER.log(Level.CONFIG, "configuration saved, game is loading");
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
