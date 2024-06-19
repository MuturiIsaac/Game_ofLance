package com.game.lanceofdestiny;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle {
    private int x, y;
    private int width, height;
    private Color color;

    public Obstacle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public boolean collidesWith(Player player) {
        return player.getX() < x + width &&
                player.getX() + player.getWidth() > x &&
                player.getY() < y + height &&
                player.getY() + player.getHeight() > y;
    }

    public boolean collidesWith(Spell spell) {
        return spell.getX() < x + width &&
                spell.getX() + 10 > x &&
                spell.getY() < y + height &&
                spell.getY() + 10 > y;
    }
}
