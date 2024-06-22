package com.game.lanceofdestiny;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstacle {
    private int x, y;
    private Image image;

    public Obstacle(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
