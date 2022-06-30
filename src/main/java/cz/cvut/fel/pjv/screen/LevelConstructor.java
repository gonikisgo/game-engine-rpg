package cz.cvut.fel.pjv.screen;

import cz.cvut.fel.pjv.handlers.ButtonHandler;
import cz.cvut.fel.pjv.utils.ObjectInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

/*
level's constructor UI Class
 */

public class LevelConstructor extends JPanel {
    public final static String[] names = new String[]{"Tree", "Water", "Key", "Potion", "Door", "Coin", "Player", "Monster", "Elf", "Biom", "LevelType", "Apply"};
    private final Color customPurple = new Color(137, 160, 211);
    public int gridWidth = 40;
    public int gridHeight = 30;
    public boolean isBiom, isBoss;
    public Hashtable<String, ArrayList<ObjectInfo>> objectsDict;
    public String biomChoice, bossChoice;
    public Color[] customColors = {new Color(81, 137, 82), new Color(15, 82, 186), new Color(50, 50, 64), new Color(162, 107, 243), new Color(121, 96, 76), new Color(255, 211, 0), new Color(184, 15, 10), new Color(254, 104, 0), new Color(152, 255, 152)};
    public int pressedButtonNum;
    public Cell[][] cells;
    public JFrame constructorWindow;
    public Menu menu;
    public JButton buttonTree, buttonWater, buttonKey, buttonPotion, buttonDoor, buttonCoin, buttonApply, buttonPlayer, buttonMonster, buttonElf;
    public JRadioButton snowChoiceButton, forestChoiceButton, yesBossButton, noBossButton;
    ButtonHandler buttonHandler = new ButtonHandler(this);
    JPanel panelLeft, panelRight, panelUp;
    Grid panelDown;
    int locateX = 20;
    int locateY = 80;
    JLabel biomLabel, bossLabel, objectLabel;

    public LevelConstructor(JFrame constructorWindow, Menu menu) {

        this.constructorWindow = constructorWindow;
        this.menu = menu;

        pressedButtonNum = -1;
        objectsDict = new Hashtable<>();

        this.setPreferredSize(new Dimension(800, 560));
        this.setDoubleBuffered(true);
        this.setFocusable(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(640, 560));
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

        panelUp = new JPanel();
        panelUp.setBackground(Color.WHITE);
        panelUp.setPreferredSize(new Dimension(640, 80));
        panelUp.setLayout(null);

        panelDown = new Grid();

        panelLeft.add(panelUp);
        panelLeft.add(panelDown);

        panelRight = new JPanel();
        panelRight.setBackground(Color.WHITE);
        panelRight.setLayout(null);
        panelRight.setPreferredSize(new Dimension(160, 560));

        this.add(panelLeft);
        this.add(panelRight);
        createButtons();
    }

    private JButton setButton(int x, int y, String name, int width, int height) {
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        panelRight.add(button);
        button.addActionListener(buttonHandler);
        return button;
    }

    private JButton setButton(String name, int width, int height, Color color) {
        JButton tmpButton = setButton(locateX, locateY, name, width, height);
        tmpButton.setForeground(color);
        locateY += 45;
        return tmpButton;
    }

    private JRadioButton setRadioButton(int x, int y, String name, int width, int height) {
        JRadioButton tmpButton = new JRadioButton(name);
        tmpButton.setBounds(x, y, width, height);
        panelUp.add(tmpButton);
        tmpButton.addActionListener(buttonHandler);
        return tmpButton;
    }

    private void setRadButtonGroup(JRadioButton button1, JRadioButton button2) {
        ButtonGroup tmpButGroup = new ButtonGroup();
        tmpButGroup.add(button1);
        tmpButGroup.add(button2);
    }

    private JLabel setLabel(String text, int x, int y, int width, int height, Color color, int fontSize, JPanel panel) {
        JLabel tmpLabel = new JLabel(text);
        tmpLabel.setBounds(x, y, width, height);
        tmpLabel.setForeground(color);
        tmpLabel.setFont(new Font(null, Font.PLAIN, fontSize));
        panel.add(tmpLabel);
        return tmpLabel;
    }

    private void createButtons() {
        objectLabel = setLabel("Object to locate:", 20, 24, 120, 20, Color.BLACK, 14, panelRight);

        buttonTree = setButton(names[0], 120, 40, customColors[0]);
        buttonWater = setButton(names[1], 120, 40, customColors[1]);
        buttonKey = setButton(names[2], 120, 40, customColors[2]);
        buttonPotion = setButton(names[3], 120, 40, customColors[3]);
        buttonDoor = setButton(names[4], 120, 40, customColors[4]);
        buttonCoin = setButton(names[5], 120, 40, customColors[5]);
        buttonPlayer = setButton(names[6], 120, 40, customColors[6]);
        buttonMonster = setButton(names[7], 120, 40, customColors[7]);
        buttonElf = setButton(names[8], 120, 40, customColors[8]);


        buttonApply = setButton(10, 495, names[11], 140, 50);


        biomLabel = setLabel("Choose biom", 20, 24, 100, 20, Color.BLACK, 14, panelUp);

        snowChoiceButton = setRadioButton(130, 25, "Snow", 70, 20);
        forestChoiceButton = setRadioButton(195, 25, "Forest", 90, 20);
        setRadButtonGroup(snowChoiceButton, forestChoiceButton);

        bossLabel = setLabel("Will this be a boss label?", 310, 24, 200, 20, Color.BLACK, 14, panelUp);

        yesBossButton = setRadioButton(510, 25, "Yes", 60, 20);
        noBossButton = setRadioButton(570, 25, "No", 50, 20);
        setRadButtonGroup(yesBossButton, noBossButton);
    }

    public static class Alert {
        public Alert(String text) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, text);
        }
    }

    public class Grid extends JPanel {
        public Grid() {
            this.setPreferredSize(new Dimension(640, 480));
            this.setDoubleBuffered(true);
            this.setFocusable(false);
            this.setLayout(new GridBagLayout());
            cells = new Cell[gridWidth][gridHeight];
            GridBagConstraints containers = new GridBagConstraints();
            for (int x = 0; x < gridWidth; x++) {
                containers.gridx = x;
                for (int y = 0; y < gridHeight; y++) {
                    containers.gridy = y;
                    cells[x][y] = new Cell();
                    this.add(cells[x][y], containers);
                }
            }
        }
    }

    public class Cell extends JPanel {
        public Cell() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (pressedButtonNum != -1) {
                        colorCell(customColors[pressedButtonNum]);
                    }
                }
            });
            setBorder(new LineBorder(customPurple, 1));
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(16, 16);
        }

        public void colorCell(Color color) {
            if (getBackground() != color) {
                setBackground(color);
            } else {
                setBackground(null);
            }
        }
    }

}
