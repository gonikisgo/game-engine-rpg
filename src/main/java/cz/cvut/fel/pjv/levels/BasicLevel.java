package cz.cvut.fel.pjv.levels;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class creates base map from .txt file
 * @author kiselnik
 */

public class BasicLevel {
    private final static Logger LOGGER = Logger.getLogger(BasicLevel.class.getName());
    public BasicObject[][] tiles;
    public boolean isBossLevel;
    public String biom;
    BufferedImage[] background;
    GamePanel gamePanel;
    int mapWidth, mapHeight;

    public BasicLevel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        isBossLevel = gamePanel.readJsonInfo.dictionary.get("LevelType").get(0).getOptions().equals("Boss");
        biom = gamePanel.readJsonInfo.dictionary.get("Biom").get(0).getOptions();
        mapWidth = GamePanel.tilesWidth * 3; // the world width is 60 tiles
        mapHeight = GamePanel.tilesHeight * 3; // the world height is 45 tiles
        tiles = new BasicObject[mapHeight][mapWidth];
        setBiomImages();
        loadTxtMap();
    }


    private void setBiomImages() {
        try {
            background = new BufferedImage[2];
            switch (biom) {
                case "Forest":
                    background[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
                    background[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
                    break;
                case "Snow":
                    background[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/snow.png"));
                    background[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/ice.png"));
                    break;
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "tiles images weren't loaded", ex);
        }
    }

    private void loadTxtMap() {
        InputStream inputStream = getClass().getResourceAsStream("/game_info/default_map.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String tmpLine;
        try {
            for (int y = 0; y < mapHeight; y++) {
                tmpLine = bufferedReader.readLine();
                String[] tileNumbers = tmpLine.split(" ");
                for (int x = 0; x < mapWidth; x++) {
                    int number = Integer.parseInt(tileNumbers[x]);
                    tiles[y][x] = new BasicObject();
                    tiles[y][x].image = background[number];
                    tiles[y][x].collision = (number == 1);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "map wasn't read from .txt file", ex);
        }

    }

    public void draw(Graphics2D graphics2D) {
        for (int worldRow = 0; worldRow < mapHeight; worldRow++) {
            for (int worldCol = 0; worldCol < mapWidth; worldCol++) {
                tiles[worldRow][worldCol].objectWorldX = worldCol * GamePanel.tileSize;
                tiles[worldRow][worldCol].objectWorldY = worldRow * GamePanel.tileSize;

                tiles[worldRow][worldCol].draw(graphics2D, gamePanel);
            }
        }
    }
}
