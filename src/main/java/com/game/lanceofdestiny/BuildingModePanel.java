package com.game.lanceofdestiny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

class BuildingModePanel extends JPanel {
    private JPanel gameArea;
    private JPanel controlPanel;
    private Map<String, JSpinner> barrierCounters;

    public BuildingModePanel() {
        setLayout(new BorderLayout());

        gameArea = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw background and barriers here
            }
        };
        gameArea.setPreferredSize(new Dimension(600, 500));

        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Barrier Controls"));

        barrierCounters = new HashMap<>();
        String[] barrierTypes = {"Simple", "Firm", "Explosive", "Gift"};

        for (String type : barrierTypes) {
            JPanel counterPanel = new JPanel();
            JLabel label = new JLabel(type + " Barriers:");
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            counterPanel.add(label);
            counterPanel.add(spinner);
            controlPanel.add(counterPanel);
            barrierCounters.put(type, spinner);
        }

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            // TODO: Validate barrier counts and switch to game mode
        });
        controlPanel.add(startGameButton);

        add(gameArea, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

        JLabel editingLabel = new JLabel("EDITING AREA FOR THE PLAYER");
        editingLabel.setHorizontalAlignment(JLabel.CENTER);
        add(editingLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: Handle barrier placement
            }
        });
    }
}
