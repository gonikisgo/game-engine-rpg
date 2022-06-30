package cz.cvut.fel.pjv.handlers;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
class for listening keyboard buttons during the game
 */

public class KeyHandler implements KeyListener {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());

    public boolean upPressed, leftPressed, rightPressed, downPressed, attackUpPressed, attackLeftPressed, attackDownPressed, attackRightPressed, pausePressed, isMenuBigSword, weaponChoice, buyWeapon, saveGameButton, moneyCheat;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_UP:
                attackUpPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                attackLeftPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                attackDownPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                attackRightPressed = true;
                break;
            case KeyEvent.VK_O:
                isMenuBigSword = isMenuBigSword != true;
                break;
            case KeyEvent.VK_P:
                weaponChoice = weaponChoice != true;
                break;
            case KeyEvent.VK_ESCAPE:
                pausePressed = pausePressed != true;
                if (pausePressed) {
                    LOGGER.log(Level.WARNING, "game paused");
                } else {
                    LOGGER.log(Level.WARNING, "game resumed");
                }
                break;
            case KeyEvent.VK_Y:
                saveGameButton = saveGameButton != true;
                break;
            case KeyEvent.VK_4:
                moneyCheat = true;
                break;
            case KeyEvent.VK_ENTER:
                buyWeapon = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_UP:
                attackUpPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                attackLeftPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                attackDownPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                attackRightPressed = false;
                break;
            case KeyEvent.VK_4:
                moneyCheat = false;
                break;
        }
    }
}
