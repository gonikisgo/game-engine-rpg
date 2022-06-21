package cz.cvut.fel.pjv.levels;

import cz.cvut.fel.pjv.objects.stat1c.Coin;
import cz.cvut.fel.pjv.objects.stat1c.Door;
import cz.cvut.fel.pjv.objects.stat1c.Tree;
import cz.cvut.fel.pjv.objects.stat1c.Water;

import java.awt.image.BufferedImage;

public class BasicLevel {
    public BufferedImage[] tiles;
    public boolean isBossLevel;
    public String biom;
    public Tree[] trees;
    public Water[] water;
    public Door[] doors;
    public Coin[] coins;
}
