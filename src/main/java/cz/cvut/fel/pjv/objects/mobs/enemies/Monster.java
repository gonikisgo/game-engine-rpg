package cz.cvut.fel.pjv.objects.mobs.enemies;

import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Monster extends BasicMob {
    public Monster(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        name = "Monster";
        collision = true;
        speed = 1;
        delta = 2;

        try {
            standing = ImageIO.read(getClass().getResourceAsStream("/mobs/standing.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/mobs/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/mobs/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/mobs/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/mobs/left2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/mobs/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/mobs/down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/mobs/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/mobs/right2.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
