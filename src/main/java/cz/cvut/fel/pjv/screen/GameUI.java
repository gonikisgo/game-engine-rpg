package cz.cvut.fel.pjv.screen;

import javax.swing.*;
import java.awt.*;

import static cz.cvut.fel.pjv.screen.GamePanel.fps;

public class GameUI {
    Thread newThread;
    GamePanel gamePanel;
    PanelXP panelXP;
    PanelItems panelItems;
    Font customFont = new Font(null, Font.PLAIN, 20);
    public void loadGame() {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Game Window");
        UIpanel uIpanel = new UIpanel();

        gameWindow.add(uIpanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }
    
    public class UIpanel extends JPanel implements Runnable {
        public UIpanel() {
            this.setPreferredSize(new Dimension(800, 528));
            this.setBackground(Color.white);
            this.setDoubleBuffered(true);
            this.setLayout(new BorderLayout());

            gamePanel = new GamePanel();
            panelXP = new PanelXP(gamePanel);
            panelItems = new PanelItems(gamePanel);

            this.add(panelXP, BorderLayout.NORTH);
            this.add(gamePanel, BorderLayout.SOUTH);
            this.add(panelItems, BorderLayout.CENTER);

            this.starUIthread();
        }

        public void starUIthread() {
            newThread = new Thread(this);
            newThread.start();
        }


        @Override
        public void run() {
            double drawInterval = 1000000000 / fps;
            double nextDraw = drawInterval + System.nanoTime();

            while (newThread != null) {
                if (!gamePanel.keyHandler.attackPressed) {
                    update();
                    repaint();
                }

                try {
                    double remain = (nextDraw - System.nanoTime()) / 1000000;
                    remain = remain < 0 ? 0 : remain;
                    Thread.sleep((long)remain);
                    nextDraw += drawInterval;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void update() {
            gamePanel.player.update();
            for (int i = 0; i < gamePanel.allMobs.size(); i++) {
                gamePanel.allMobs.get(i).update(i);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;

            gamePanel.basicLevel.draw(graphics2D);

            for (int i = 0; i < gamePanel.allObjects.size(); i++) {
                gamePanel.allObjects.get(i).draw(graphics2D, gamePanel);
            }
            for (int i = 0; i < gamePanel.allMobs.size(); i++) {
                gamePanel.allMobs.get(i).draw(graphics2D, gamePanel);
            }
            gamePanel.player.draw(graphics2D);
            panelXP.draw(graphics2D);
            panelItems.draw(graphics2D);

            graphics2D.dispose();
        }
    }
}
