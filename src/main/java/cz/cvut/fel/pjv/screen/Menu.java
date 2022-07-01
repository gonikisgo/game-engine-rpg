package cz.cvut.fel.pjv.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * start menu is running on this panel
 * @author kiselnik
 */

public class Menu implements ActionListener {
    private final Color customPurple = new Color(137, 160, 211);
    public GameUI gameUI = new GameUI();
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
        panel.setBackground(customPurple);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            menuWindow.dispose();
            loadConstructor();
        } else if (e.getSource() == loadButton) {
            menuWindow.dispose();
            gameUI.loadGame();
        }
    }
}
