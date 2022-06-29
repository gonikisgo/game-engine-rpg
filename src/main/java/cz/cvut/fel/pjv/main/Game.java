package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.levels.LevelConstructor;
import cz.cvut.fel.pjv.levels.Menu;
import cz.cvut.fel.pjv.screen.GamePanel;
import cz.cvut.fel.pjv.utils.WriteJson;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setMenu();
    }
}

