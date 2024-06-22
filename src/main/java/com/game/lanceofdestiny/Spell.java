package com.game.lanceofdestiny;

import java.io.Serializable;

public class Spell implements Serializable {
    public enum SpellType {
        FIRE, ICE, LIGHTNING
    }

    private SpellType spellType;

    public Spell(SpellType spellType) {
        this.spellType = spellType;
    }

    public SpellType getSpellType() {
        return spellType;
    }
}
