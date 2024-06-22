package com.game.lanceofdestiny;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GamePanel extends JPanel implements KeyListener {
    private GameClient client;
    private Player player;
    private ArrayList<Spell> spells;
    private ArrayList<Obstacle> obstacles;
    private Image backgroundImage;

    public GamePanel(GameClient client) {
        this.client = client;
        this.player = new Player(100, 100);
        this.spells = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        // Load background image
        this.backgroundImage = new ImageIcon(getClass().getResource("/resources/background.png")).getImage();

        // Add some obstacles
        obstacles.add(new Obstacle(300, 200, "/resources/red_gem.png"));
        obstacles.add(new Obstacle(500, 400, "/resources/spear.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Draw the player
        player.draw(g);

        // Draw spells
        for (Spell spell : spells) {
            spell.draw(g);
        }

        // Draw obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, 0, -1));
                player.move(0, -1);
                break;
            case KeyEvent.VK_S:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, 0, 1));
                player.move(0, 1);
                break;
            case KeyEvent.VK_A:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, -1, 0));
                player.move(-1, 0);
                break;
            case KeyEvent.VK_D:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, 1, 0));
                player.move(1, 0);
                break;
            case KeyEvent.VK_1:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.CAST_SPELL, Spell.SpellType.FIRE));
                spells.add(new Spell(player.getX(), player.getY(), Spell.SpellType.FIRE));
                break;
            case KeyEvent.VK_2:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.CAST_SPELL, Spell.SpellType.ICE));
                spells.add(new Spell(player.getX(), player.getY(), Spell.SpellType.ICE));
                break;
            case KeyEvent.VK_3:
                client.sendAction(new PlayerAction(PlayerAction.ActionType.CAST_SPELL, Spell.SpellType.LIGHTNING));
                spells.add(new Spell(player.getX(), player.getY(), Spell.SpellType.LIGHTNING));
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
