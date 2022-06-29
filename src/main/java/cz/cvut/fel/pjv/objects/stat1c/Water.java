package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Water extends BasicObject {
    public Water() {
        name = "Water";
        collision = true;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/water.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
