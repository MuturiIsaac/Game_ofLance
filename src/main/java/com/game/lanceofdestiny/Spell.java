package com.game.lanceofdestiny;

import java.awt.Graphics;
import java.io.Serializable;

public class Spell implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum SpellType {
        FIRE, ICE, LIGHTNING
    }

    private SpellType spellType;
    private int power;
    private int x, y; // Add these instance variables

    public Spell(SpellType spellType, int power, int x, int y) {
        this.spellType = spellType;
        this.power = power;
        this.x = x;
        this.y = y;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public int getPower() {
        return power;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        // Implement this method to draw the spell on the screen
    }

    public void move() {
        // Implement this method to move the spell
    }
}