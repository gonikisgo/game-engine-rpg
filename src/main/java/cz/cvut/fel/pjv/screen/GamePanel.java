package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.handlers.MouseHandler;
import cz.cvut.fel.pjv.handlers.ObjectHandler;
import cz.cvut.fel.pjv.handlers.StateHandler;
import cz.cvut.fel.pjv.levels.BasicLevel;
import cz.cvut.fel.pjv.levels.LevelConstructor;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.ObjectSet;
import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.objects.stat1c.Key;
import cz.cvut.fel.pjv.utils.ReadJson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    JPanel panelLeft, panelRight, panelUp, panelDown;
    public KeyHandler keyHandler = new KeyHandler();
    public ObjectHandler objectHandler;
    public MouseHandler mouseHandler;
    public StateHandler stateHandler = new StateHandler(this);
    public GameUI gameUi;
    public ReadJson readJsonInfo = new ReadJson();
    public BasicLevel basicLevel = new BasicLevel(this);
    Thread newThread;
    public Player player = new Player(this, keyHandler);
    public ArrayList<BasicObject> allObjects = new ArrayList<BasicObject>();
    public ObjectSet objectSet = new ObjectSet(this);

    GameUI gameUI = new GameUI(this);
    final static int fps = 30;

    public final static int tileSize = 32; // every tile is 32*32 pixels
    // the tiles' layout is 20*15
    public final static int tilesWidth = 20;
    public final static int tilesHeight = 15;
    // the pixels' layout is 640*480
    public final static int pixelsWidth = tilesWidth * tileSize;
    public final static int pixelsHeight = tilesHeight * tileSize;



    public GamePanel() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);
        // this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        /*
        panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(640, 480));
        panelLeft.setDoubleBuffered(true);
        panelLeft.addKeyListener(keyHandler);
        panelLeft.setFocusable(true);
        panelLeft.setLayout(null);


        panelRight = new JPanel();
        panelRight.setBackground(Color.WHITE);
        panelRight.setLayout(null);
        panelRight.setPreferredSize(new Dimension(160, 480));

        this.add(panelLeft);
        this.add(panelRight);

         */

        makeSetup();
    }


    public void makeSetup() {
        objectSet.objectSetter();
    }



    public void startGameThread() {
        newThread = new Thread(this);
        newThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDraw = drawInterval + System.nanoTime();

        while (newThread != null) {
            update();
            repaint();

            try {
                double remain = (nextDraw - System.nanoTime()) / 1000000;
                remain = remain < 0 ? 0 : remain;
                Thread.sleep((long)remain);
                nextDraw += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;

        basicLevel.draw(graphics2D);

        for (int i = 0; i < allObjects.size(); i++) {
            allObjects.get(i).draw(graphics2D, this);
        }
        player.draw(graphics2D);
        //gameUI.draw(graphics2D);

        graphics2D.dispose();
    }
}
