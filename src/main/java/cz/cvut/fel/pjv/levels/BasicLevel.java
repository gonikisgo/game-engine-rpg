package cz.cvut.fel.pjv.levels;

import cz.cvut.fel.pjv.main.Game;
import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.stat1c.Coin;
import cz.cvut.fel.pjv.objects.stat1c.Door;
import cz.cvut.fel.pjv.objects.stat1c.Tree;
import cz.cvut.fel.pjv.objects.stat1c.Water;
import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BasicLevel {
    BufferedImage background[];
    GamePanel gamePanel;
    public BasicObject[][] tiles;
    public boolean isBossLevel;
    public String biom;
    public Tree[] trees;
    public Water[] water;
    public Door[] doors;
    public Coin[] coins;

    public int worldX;
    public int worldY;

    public int screenX;
    public int screenY;

    public BasicLevel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new BasicObject[gamePanel.tilesHeight * 3][gamePanel.tilesWidth * 3];
        GetMap();
    }


    public void GetMap() {
        setBiomImages();
        loadTxtMap();
    }

    public void setBiomImages() {
        try {
            background = new BufferedImage[2];
            switch (gamePanel.readJsonInfo.dictionary.get("Biom").get(0).getOptions()) {
                case "Forest":
                    background[0] = ImageIO.read(getClass().getResourceAsStream("/bioms/grass.png"));
                    background[1] = ImageIO.read(getClass().getResourceAsStream("/bioms/water.png"));
                    break;
                case "Snow":
                    background[0] = ImageIO.read(getClass().getResourceAsStream("/bioms/snow.png"));
                    background[1] = ImageIO.read(getClass().getResourceAsStream("/bioms/ice.png"));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTxtMap() {
        InputStream inputStream = getClass().getResourceAsStream("/game_info/default_map.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String tmpLine;
        try {
            for (int y = 0; y < gamePanel.tilesHeight * 3; y++) {
                tmpLine = bufferedReader.readLine();
                String tileNumbers[] = tmpLine.split(" ");
                for (int x = 0; x < gamePanel.tilesWidth * 3; x++) {
                    int number = Integer.parseInt(tileNumbers[x]);
                    tiles[y][x] = new BasicObject();
                    tiles[y][x].image = background[number];
                    tiles[y][x].collision = (number == 1);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void draw(Graphics2D graphics2D) {
        for (int worldRow = 0; worldRow < gamePanel.tilesHeight * 3; worldRow++) {
            for (int worldCol = 0; worldCol < gamePanel.tilesWidth * 3; worldCol++) {

                worldX = worldCol * gamePanel.tileSize;
                worldY = worldRow * gamePanel.tileSize;
                screenX = worldX - gamePanel.player.objectWorldX + Player.screenX;
                screenY = worldY - gamePanel.player.objectWorldY + Player.screenY;

                if (worldX + gamePanel.tileSize > gamePanel.player.objectWorldX - Player.screenX &&
                        worldX - gamePanel.tileSize < gamePanel.player.objectWorldX + Player.screenX &&
                        worldY + gamePanel.tileSize > gamePanel.player.objectWorldY - Player.screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player.objectWorldY + Player.screenY) {
                    graphics2D.drawImage(tiles[worldRow][worldCol].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
}
