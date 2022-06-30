package cz.cvut.fel.pjv.objects.stat1c.weapon;

import cz.cvut.fel.pjv.objects.BasicObject;

import java.awt.image.BufferedImage;

/*
base class for all weapon
 */

public class BasicWeapon extends BasicObject {
    public BufferedImage upAttack, leftAttack, downAttack, rightAttack;
    public int playerHandOffset = 10; // player's hand starts from 22 pixel
}
