package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class Tree extends BasicObject {

    InputStream inputStream;
    GamePanel gamePanel;
    public Tree(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Tree";
        collision = true;

        switch (gamePanel.basicLevel.biom) {
            case "Forest":
                inputStream = getClass().getResourceAsStream("/objects/tree.png");
                collision = true;
                break;
            case "Snow":
                inputStream = getClass().getResourceAsStream("/objects/tree_snow.png");
                break;
        }
        try {
            this.image = ImageIO.read(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
