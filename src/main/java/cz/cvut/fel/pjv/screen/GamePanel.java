package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.handlers.MouseHandler;
import cz.cvut.fel.pjv.handlers.ObjectHandler;
import cz.cvut.fel.pjv.handlers.StateHandler;
import cz.cvut.fel.pjv.levels.BasicLevel;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.ObjectSet;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.utils.ReadJson;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public KeyHandler keyHandler = new KeyHandler();
    public ObjectHandler objectHandler;
    public MouseHandler mouseHandler;
    public StateHandler stateHandler = new StateHandler(this);
    public ReadJson readJsonInfo = new ReadJson();
    public BasicLevel basicLevel = new BasicLevel(this);
    Thread newThread;
    public Player player = new Player(this, keyHandler);
    public ArrayList<BasicObject> allObjects = new ArrayList<BasicObject>();
    public ArrayList<BasicMob> allMobs = new ArrayList<BasicMob>();
    public ObjectSet objectSet = new ObjectSet(this);
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
        objectSet.objectSetter();
    }
}
