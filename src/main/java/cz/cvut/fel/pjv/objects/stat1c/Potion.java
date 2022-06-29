package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Potion extends BasicObject {
    public int extraDamage;

    public Potion() {
        name = "Potion";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
