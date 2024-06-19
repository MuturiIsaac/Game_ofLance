package com.game.lanceofdestiny;

import java.io.Serializable;

public class PlayerAction implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum ActionType {
        MOVE, CAST_SPELL
    }

    private ActionType actionType;
    private int x;
    private int y;
    private Spell.SpellType spellType;

    public PlayerAction(ActionType actionType, int x, int y) {
        this.actionType = actionType;
        this.x = x;
        this.y = y;
    }

    public PlayerAction(ActionType actionType, Spell.SpellType spellType) {
        this.actionType = actionType;
        this.spellType = spellType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Spell.SpellType getSpellType() {
        return spellType;
    }
}
