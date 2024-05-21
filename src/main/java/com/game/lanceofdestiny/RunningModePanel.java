package com.game.lanceofdestiny;

import javax.swing.*;
import java.awt.*;

public class RunningModePanel extends JPanel {
    public RunningModePanel() {
        setLayout(new BorderLayout());

        // Create UI components for running mode
        JPanel topPanel = new JPanel();
        // Add components for displaying score, lives, pause/resume, etc.

        JPanel centerPanel = new JPanel();
        // Add components for rendering the game area

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}