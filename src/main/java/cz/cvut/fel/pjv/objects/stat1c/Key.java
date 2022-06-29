package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends BasicObject {
    public Key() {
        name = "Key";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
