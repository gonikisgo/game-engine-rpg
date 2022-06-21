package cz.cvut.fel.pjv.handlers;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseEvent {

    public MouseHandler(Component source, int id, long when, int modifiers, int x, int y, int xAbs, int yAbs, int clickCount, boolean popupTrigger, int button) {
        super(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, button);
    }
}
