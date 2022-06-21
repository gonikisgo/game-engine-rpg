package cz.cvut.fel.pjv.objects.mobs;

import cz.cvut.fel.pjv.handlers.KeyHandler;
import cz.cvut.fel.pjv.objects.stat1c.Coin;
import cz.cvut.fel.pjv.objects.stat1c.Key;
import cz.cvut.fel.pjv.objects.stat1c.Potion;
import cz.cvut.fel.pjv.objects.stat1c.weapon.BasicWeapon;
import cz.cvut.fel.pjv.screen.GamePanel;

public class Player extends BasicMob {
    public int level;
    public Key[] keys;
    public BasicWeapon[] weapons;
    public Coin[] coins;
    public Potion[] potion;
}
