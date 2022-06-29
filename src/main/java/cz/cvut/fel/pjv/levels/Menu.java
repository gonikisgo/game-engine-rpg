package cz.cvut.fel.pjv.levels;

import cz.cvut.fel.pjv.screen.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    public JPanel panelLeft, panelRight, panelUp;
    JFrame menuWindow = new JFrame();
    JButton startButton = new JButton("New Game");
    JButton loadButton = new JButton("Load Game");
    JLabel label = new JLabel("2D Role-Play-Game");

    public void setMenu() {

        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.setTitle("Menu");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(640, 480));
        panel.setBackground(new Color( 137, 160, 211));
        panel.setDoubleBuffered(true);
        panel.setLayout(null);

        label.setBounds(200, 80, 300, 60);
        label.setForeground(Color.BLACK);
        label.setFont(new Font(null, Font.PLAIN, 25));
        panel.add(label);

        startButton.setBounds(220, 220, 200, 80);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        panel.add(startButton);

        loadButton.setBounds(220, 330, 200, 80);
        loadButton.setFocusable(false);
        loadButton.addActionListener(this);
        panel.add(loadButton);

        menuWindow.add(panel);
        menuWindow.pack();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }


    public void loadConstructor() {
        JFrame constructorWindow = new JFrame();
        constructorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        constructorWindow.setResizable(false);
        constructorWindow.setTitle("RPG constructor");

        LevelConstructor levelConstructor = new LevelConstructor(constructorWindow, this);
        constructorWindow.add(levelConstructor);
        constructorWindow.pack();
        constructorWindow.setLocationRelativeTo(null);
        constructorWindow.setVisible(true);
    }

    public void loadGame() {

        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Game Window");

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(800, 560));
        jPanel.setBackground(Color.white);
        jPanel.setDoubleBuffered(true);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));


        panelLeft = new JPanel();
        panelLeft.setBackground(Color.WHITE);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.setPreferredSize(new Dimension(640, 560));


        panelUp = new JPanel();
        panelUp.setBackground(Color.WHITE);
        panelUp.setLayout(null);
        panelUp.setPreferredSize(new Dimension(640, 80));

        GamePanel gamePanel = new GamePanel();

        panelLeft.add(panelUp);
        panelLeft.add(gamePanel);


        panelRight = new JPanel();
        panelRight.setBackground(Color.WHITE);
        panelRight.setLayout(null);
        panelRight.setPreferredSize(new Dimension(160, 560));

        jPanel.add(panelLeft);
        jPanel.add(panelRight);


        gameWindow.add(jPanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.startGameThread();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            menuWindow.dispose();
            loadConstructor();
        } else if (e.getSource() == loadButton) {
            menuWindow.dispose();
            loadGame();
        }
    }
}
