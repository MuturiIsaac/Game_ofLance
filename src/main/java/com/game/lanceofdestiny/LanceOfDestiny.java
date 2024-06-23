package com.game.lanceofdestiny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class LanceOfDestiny extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private IntroPanel introPanel;
    private BuildingModePanel buildingModePanel;

    public LanceOfDestiny() {
        setTitle("Lance of Destiny");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        introPanel = new IntroPanel();
        buildingModePanel = new BuildingModePanel();

        mainPanel.add(introPanel, "Intro");
        mainPanel.add(buildingModePanel, "BuildingMode");

        add(mainPanel);

        // Add key listener to switch to building mode
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    cardLayout.show(mainPanel, "BuildingMode");
                }
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LanceOfDestiny());
    }
}

