package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.handlers.MouseHandler;
import cz.cvut.fel.pjv.handlers.ObjectHandler;
import cz.cvut.fel.pjv.handlers.StateHandler;
import cz.cvut.fel.pjv.objects.mobs.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public KeyHandler keyHandler;
    public ObjectHandler objectHandler;
    public MouseHandler mouseHandler;
    public StateHandler stateHandler;
    public GameUI gameUi;

    public GamePanel() {
    }

    public void startGameThread() {
    }

    @Override
    public void run() {
    }

    public void update() {
    }

    public void paintComponent(Graphics g) {
    }

}
