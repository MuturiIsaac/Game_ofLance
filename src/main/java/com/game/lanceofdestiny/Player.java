package com.game.lanceofdestiny;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private int x, y;
    private Image image;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(getClass().getResource("/resources/heart.png")).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
