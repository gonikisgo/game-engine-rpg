package cz.cvut.fel.pjv.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, rightPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int numButtonPressed = e.getKeyCode();

        if (numButtonPressed == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (numButtonPressed == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (numButtonPressed == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (numButtonPressed == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int numButtonUnpressed = e.getKeyCode();

        if (numButtonUnpressed == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (numButtonUnpressed == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (numButtonUnpressed == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (numButtonUnpressed == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
