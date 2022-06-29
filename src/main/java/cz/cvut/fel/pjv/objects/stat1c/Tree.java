package cz.cvut.fel.pjv.objects.stat1c;

import cz.cvut.fel.pjv.objects.BasicObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tree extends BasicObject {
    public Tree() {
        name = "Tree";
        collision = true;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/tree.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
