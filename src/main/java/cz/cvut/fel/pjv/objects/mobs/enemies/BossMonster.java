package cz.cvut.fel.pjv.objects.mobs.enemies;

import cz.cvut.fel.pjv.objects.mobs.BasicMob;
import cz.cvut.fel.pjv.objects.mobs.Player;
import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static cz.cvut.fel.pjv.screen.GamePanel.tileSize;

public class BossMonster extends BasicMob {
    public BossMonster() {
        this.gamePanel = gamePanel;

        name = "Boss";
        collision = true;
        speed = 7;
        delta = 10;
        solidAreaWidthX = 64;
        solidAreaHeightY = 64;

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
