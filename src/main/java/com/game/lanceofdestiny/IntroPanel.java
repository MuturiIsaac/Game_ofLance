package com.game.lanceofdestiny;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class IntroPanel extends JPanel {
    private BufferedImage background;
    private Map<String, BufferedImage> sprites = new HashMap<>();
    private int staffX, staffY;
    private int fireBallX, fireBallY;

    public IntroPanel() {
        loadResources();
        setupInitialPositions();
    }

    private void loadResources() {
        try {
            background = ImageIO.read(getClass().getResource("/background.png"));
            sprites.put("staff", ImageIO.read(getClass().getResource("/staff.png")));
            sprites.put("fireball", ImageIO.read(getClass().getResource("/fireball.png")));
            sprites.put("heart", ImageIO.read(getClass().getResource("/heart.png")));
            sprites.put("barrier_white", ImageIO.read(getClass().getResource("/barrier_white.png")));
            sprites.put("barrier_red", ImageIO.read(getClass().getResource("/barrier_red.png")));
            sprites.put("barrier_green", ImageIO.read(getClass().getResource("/Blue")));
            sprites.put("barrier_blue", ImageIO.read(getClass().getResource("/images/barrier_blue.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupInitialPositions() {
        staffX = getWidth() / 2 - sprites.get("staff").getWidth() / 2;
        staffY = getHeight() - sprites.get("staff").getHeight() - 20;
        fireBallX = staffX + sprites.get("staff").getWidth() / 2 - sprites.get("fireball").getWidth() / 2;
        fireBallY = staffY - sprites.get("fireball").getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw background
        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        // Draw staff
        g2d.drawImage(sprites.get("staff"), staffX, staffY, null);

        // Draw fireball
        g2d.drawImage(sprites.get("fireball"), fireBallX, fireBallY, null);

        // Draw barriers (example positions)
        drawBarriers(g2d);

        // Draw hearts (lives)
        drawLives(g2d);

        // Draw intro text
        drawIntroText(g2d);
    }

    private void drawBarriers(Graphics2D g2d) {
        // Example barrier placement - you'll want to randomize this
        g2d.drawImage(sprites.get("barrier_white"), 100, 100, null);
        g2d.drawImage(sprites.get("barrier_red"), 200, 150, null);
        g2d.drawImage(sprites.get("barrier_green"), 300, 100, null);
        g2d.drawImage(sprites.get("barrier_blue"), 400, 150, null);
    }

    private void drawLives(Graphics2D g2d) {
        for (int i = 0; i < 3; i++) {
            g2d.drawImage(sprites.get("heart"), 10 + i * 40, 10, null);
        }
    }

    private void drawIntroText(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Lance of Destiny", getWidth() / 2 - 100, 50);
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        g2d.drawString("Press SPACE to start", getWidth() / 2 - 80, getHeight() - 50);
    }
}