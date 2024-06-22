package com.game.lanceofdestiny;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Spell {
    public enum SpellType {
        FIRE, ICE, LIGHTNING
    }

    private int x, y;
    private Image image;
    private SpellType spellType;

    public Spell(int x, int y, SpellType spellType) {
        this.x = x;
        this.y = y;
        this.spellType = spellType;

        switch (spellType) {
            case FIRE:
                this.image = new ImageIcon(getClass().getResource("/resources/fireball.png")).getImage();
                break;
            case ICE:
                this.image = new ImageIcon(getClass().getResource("/resources/blue_gem.png")).getImage();
                break;
            case LIGHTNING:
                this.image = new ImageIcon(getClass().getResource("/resources/green_gem.png")).getImage();
                break;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void move() {
        // Add movement logic here if needed
    }
}
