package com.game.lanceofdestiny;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Player implements Serializable {
    private  static final long serialVersionUID = 1L;
    private int x, y;
    private int width, height; // Add these instance variables
    private int health;
    private int mana;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50; // Set default width
        this.height = 50; // Set default height
        this.health = 100; // Default health value
        this.mana = 50; // Default mana value
    }

    // Add these getter methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
