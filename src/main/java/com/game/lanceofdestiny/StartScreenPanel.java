package com.game.lanceofdestiny;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartScreenPanel extends JPanel implements KeyListener {
    private boolean startGame;

    public StartScreenPanel() {
        System.out.println("StartScreenPanel constructor");
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        startGame = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Painting StartScreenPanel");

        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.WHITE);
        g.drawString("Welcome to the Game", 200, 250);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Press Enter to Start", 300, 300);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            startGame = true;
            System.out.println("Enter pressed, starting game");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    public boolean isStartGame() {
        return startGame;
    }
}
