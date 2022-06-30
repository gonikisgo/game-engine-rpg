package cz.cvut.fel.pjv.objects.stat1c.weapon;

import cz.cvut.fel.pjv.screen.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/*
big sword class
 */
public class BigSword extends BasicWeapon {
    public BigSword(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.damage = 25;

        solidAreaX = 0;
        solidAreaY = 9;
        solidAreaWidthX = 27;
        solidAreaHeightY = 15;
        solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidthX, solidAreaHeightY);
        try {
            switch (gamePanel.basicLevel.biom) {
                case "Forest":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/forest/player/big_sword_right.png"));
                    break;

                case "Snow":
                    upAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_up.png"));
                    leftAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_left.png"));
                    downAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_down.png"));
                    rightAttack = ImageIO.read(getClass().getResourceAsStream("/mobs/snow/player/big_sword_right.png"));
                    break;
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
