package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.objects.BasicObject;
import cz.cvut.fel.pjv.objects.stat1c.HeartXP;

import java.awt.image.BufferedImage;

public class BasicMob extends BasicObject {
    public int speed;
    public boolean spriteChange = false;
    public int spriteChangeRate = 0;
    public HeartXP XP;
    public boolean canMove;
    public BufferedImage standing, up1, up2, left1, left2, down1, down2, right1, right2;
    public boolean canDamage;
}
