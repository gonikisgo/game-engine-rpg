package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.handlers.ObjectHandler;
import cz.cvut.fel.pjv.handlers.StateHandler;
import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.levels.BasicLevel;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.ObjectSet;
import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.utils.ReadJson;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * game is running on this panel
 * @author kiselnik
 */


public class GamePanel extends JPanel {
    public final static int tileSize = 32; // every tile is 32*32 pixels
    public final static int tilesWidth = 20; // game panel tiles width
    public final static int tilesHeight = 15; // game panel tiles height
    private final static int healthBarOffset = 6;
    private final static int healthBarHeight = 4;
    private final Color customDarkGreen = new Color(3, 75, 3);
    private final Color customLightGreen = new Color(154, 205, 50);
    private final Color customDarkRed = new Color(139, 0, 0);
    public int totalDamageOnMonster = 0;
    public int levelHealth = 0;
    public Random rand = new Random();
    public KeyHandler keyHandler = new KeyHandler();
    public ObjectHandler objectHandler = new ObjectHandler(this);
    public StateHandler stateHandler = new StateHandler(this);
    public WeaponHandler weaponHandler = new WeaponHandler(this);
    public ReadJson readJsonInfo;
    public BasicLevel basicLevel;
    public Player player;
    public ArrayList<BasicObject> allObjects = new ArrayList<BasicObject>();
    public ArrayList<BasicMob> allMobs = new ArrayList<BasicMob>();
    public ObjectSet objectSet;


    public GamePanel(String filePath) {
        readJsonInfo = new ReadJson(filePath);
        basicLevel = new BasicLevel(this);
        player = new Player(this, keyHandler);
        objectSet = new ObjectSet(this);

        this.setPreferredSize(new Dimension(tileSize * tilesWidth, tileSize * tilesHeight)); // game panel is 640*480
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);
        objectSet.objectSetter();
    }


    public void drawObjectHealth(Graphics2D graphics2D, BasicObject basicObject) {
        graphics2D.setColor(customDarkGreen);

        int screenX = basicObject.objectWorldX - player.objectWorldX + player.screenX;
        int screenY = basicObject.objectWorldY - player.objectWorldY + player.screenY - healthBarOffset;

        graphics2D.fillRect(screenX, screenY, tileSize, healthBarHeight);

        graphics2D.setColor(customLightGreen);
        graphics2D.fillRect(screenX, screenY, basicObject.health * tileSize / 60, healthBarHeight); // full tree health is 60
    }

    public void drawMobHealth(Graphics2D graphics2D, BasicMob basicMob) {
        graphics2D.setColor(customDarkRed);
        int screenX = basicMob.objectWorldX - player.objectWorldX + player.screenX;
        int screenY = basicMob.objectWorldY - player.objectWorldY + player.screenY - healthBarOffset;

        graphics2D.fillRect(screenX, screenY, tileSize, healthBarHeight);

        graphics2D.setColor(Color.RED);
        switch (basicMob.name) {
            case "Monster":
                graphics2D.fillRect(screenX, screenY, basicMob.health * tileSize / 100, healthBarHeight); // full monster health is 100
                break;
            case "BossMonster":
                graphics2D.fillRect(screenX, screenY, basicMob.health * tileSize / 150, healthBarHeight); // full boss monster health is 150
                break;
        }
    }
}
