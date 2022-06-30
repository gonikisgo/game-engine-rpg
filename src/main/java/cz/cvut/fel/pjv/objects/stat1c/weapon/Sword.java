package cz.cvut.fel.pjv.objects.stat1c.weapon;

import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
sword class
 */

public class Sword extends BasicWeapon {
    public Sword(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.damage = 5;

        solidAreaX = 0;
        solidAreaY = 9;
        solidAreaWidthX = 23;
        solidAreaHeightY = 15;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/sword_right.png"));
                    break;

                case "Snow":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/sword_right.png"));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
