package cz.cvut.fel.pjv.levels;

import cz.cvut.fel.pjv.objects.BasicObject;
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

    public BasicLevel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        isBossLevel = gamePanel.readJsonInfo.dictionary.get("LevelType").get(0).getOptions().equals("Boss");
        biom = gamePanel.readJsonInfo.dictionary.get("Biom").get(0).getOptions();
        tiles = new BasicObject[gamePanel.tilesHeight * 3][gamePanel.tilesWidth * 3];
        setBiomImages();
        loadTxtMap();
    }


    public void setBiomImages() {
        try {
            background = new BufferedImage[2];
            switch (biom) {
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

                int worldX = worldCol * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = worldX - (gamePanel.player.objectWorldX - gamePanel.player.screenX);
                int screenY = worldY - (gamePanel.player.objectWorldY - gamePanel.player.screenY);


                if (worldX + gamePanel.tileSize > gamePanel.player.objectWorldX - gamePanel.player.screenX &&
                        worldX - gamePanel.tileSize < gamePanel.player.objectWorldX + gamePanel.player.screenX &&
                        worldY + gamePanel.tileSize > gamePanel.player.objectWorldY - (gamePanel.player.screenY - 48) &&
                        worldY - gamePanel.tileSize < gamePanel.player.objectWorldY + (528 - gamePanel.player.screenY)) {

                        graphics2D.drawImage(tiles[worldRow][worldCol].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
}
