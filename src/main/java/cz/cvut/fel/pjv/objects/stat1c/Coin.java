package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coin extends BasicObject {
    public Coin() {
        name = "Coin";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
