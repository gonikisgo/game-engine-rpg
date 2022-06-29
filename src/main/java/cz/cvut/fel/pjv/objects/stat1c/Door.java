package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends BasicObject {
    public boolean isClosed = false;

    public Door() {
        name = "Door";
        collision = true;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
