package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.utils.ScaleOptimisation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameUI {
    public ScaleOptimisation scaleOptimisation;
    public StateRect stateRect;
    BufferedImage imageXP;
    BufferedImage imagePotion;
    BufferedImage imageKey;
    BufferedImage imageCoin;
    GamePanel gamePanel;
    Font customFont = new Font(null, Font.PLAIN, 20);

    public GameUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    
}
