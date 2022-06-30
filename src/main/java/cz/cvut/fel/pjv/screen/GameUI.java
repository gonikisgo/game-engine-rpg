package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.WeaponHandler;
import cz.cvut.fel.pjv.utils.StateConvert;
import cz.cvut.fel.pjv.utils.WriteJson;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Game window, all game's panels with thread running on it created here
 */
public class GameUI {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    final static int fps = 30;
    JFrame gameWindow = new JFrame();
    Thread gameThread;
    GamePanel gamePanel;
    StateConvert stateConvert;
    PanelHealth panelHealth;
    PanelItems panelItems;

    public void loadGame() {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("GAME");

        PanelUI panelUI = new PanelUI();

        gameWindow.add(panelUI);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }

    public class PanelUI extends JPanel implements Runnable {
        public PanelUI() {
            this.setPreferredSize(new Dimension(800, 528));
            this.setBackground(Color.white);
            this.setDoubleBuffered(true);
            this.setLayout(new BorderLayout());

            gamePanel = new GamePanel();
            panelHealth = new PanelHealth(gamePanel);
            panelItems = new PanelItems(gamePanel);

            this.add(panelHealth, BorderLayout.NORTH);
            this.add(gamePanel, BorderLayout.WEST);
            this.add(panelItems, BorderLayout.EAST);

            stateConvert = new StateConvert(gamePanel);
            this.starUIThread();
        }

        private void starUIThread() {
            gameThread = new Thread(this);
            gameThread.start();
            LOGGER.log(Level.INFO, "game thread is started");
        }

        @Override
        public void run() {
            double drawInterval = 1000000000 / fps;
            double nextDraw = drawInterval + System.nanoTime();

            while (gameThread != null) {

                if (!gamePanel.keyHandler.pausePressed) {
                    update();
                    repaint();
                } else {
                    repaint();
                }

                try {
                    double remain = (nextDraw - System.nanoTime()) / 1000000;
                    remain = remain < 0 ? 0 : remain;
                    Thread.sleep((long) remain);
                    nextDraw += drawInterval;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void update() {
            endGameCheck();
            gamePanel.player.update();
            for (int i = 0; i < gamePanel.allMobs.size(); i++) {
                gamePanel.allMobs.get(i).update(i);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;

            gamePanel.basicLevel.draw(graphics2D);

            for (int i = 0; i < gamePanel.allObjects.size(); i++) {
                gamePanel.allObjects.get(i).draw(graphics2D, gamePanel);
                if (gamePanel.allObjects.get(i).underAttack) {
                    gamePanel.drawObjectHealth(graphics2D, gamePanel.allObjects.get(i));
                }
            }
            for (int i = 0; i < gamePanel.allMobs.size(); i++) {
                gamePanel.allMobs.get(i).draw(graphics2D, gamePanel);
                if (gamePanel.allMobs.get(i).underAttack) {
                    gamePanel.drawMobHealth(graphics2D, gamePanel.allMobs.get(i));
                }
            }
            gamePanel.player.draw(graphics2D);
            panelHealth.draw(graphics2D);
            panelItems.draw(graphics2D);

            graphics2D.dispose();
        }

        private void endGameCheck() {
            if (gamePanel.player.health <= 0) {
                JOptionPane.showMessageDialog(null, "YOU LOSE");
                endgame();
            }
            if (gamePanel.totalDamageOnMonster == gamePanel.levelHealth) {
                JOptionPane.showMessageDialog(null, "YOU WIN!");
                endgame();
            }
            if (gamePanel.keyHandler.saveGameButton) {
                stateConvert.convertAllState();
                new WriteJson(stateConvert.outputDict);
                JOptionPane.showMessageDialog(null, "GAME SAVED, GOODBYE");
                endgame();
            }

            if (gamePanel.keyHandler.moneyCheat) {
                gamePanel.player.coins += 10;
            }
        }

        private void endgame() {
            LOGGER.log(Level.WARNING, "game over");
            gameWindow.dispose();
            gameThread.stop();
        }
    }
}
