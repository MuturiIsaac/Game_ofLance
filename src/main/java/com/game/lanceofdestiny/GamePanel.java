package com.game.lanceofdestiny;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Player player;
    private ArrayList<Spell> spells;
    private ArrayList<Obstacle> obstacles;
    private Timer timer;
    private GameClient client;
    private boolean gameRunning;
    private Font hudFont;
    private Color backgroundColor;
    private SoundEffect spellCastSound;
    private SoundEffect collisionSound;
    private SoundEffect gameOverSound;

    public GamePanel(GameClient client) {
        this.client = client;

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        player = new Player(100, 100);
        spells = new ArrayList<>();
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(300, 200, 100, 100, Color.RED));

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();

        hudFont = new Font("Arial", Font.BOLD, 18);
        backgroundColor = Color.DARK_GRAY;
        gameRunning = true;

        spellCastSound = new SoundEffect("/spell_cast.wav");
        collisionSound = new SoundEffect("/spell_cast.wav");
        gameOverSound = new SoundEffect("/game_over.wav");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        setBackground(backgroundColor);

        if (gameRunning) {
            player.draw(g);
            for (Spell spell : spells) {
                spell.draw(g);
            }
            for (Obstacle obstacle : obstacles) {
                obstacle.draw(g);
            }
            drawHUD(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawHUD(Graphics g) {
        g.setFont(hudFont);
        g.setColor(Color.WHITE);
        g.drawString("Health: " + player.getHealth(), 10, 20);
        g.drawString("Mana: " + player.getMana(), 10, 40);
        g.drawString("Press 1-3 to cast spells", 10, 60);
    }

    private void drawGameOver(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.RED);
        g.drawString("Game Over", 300, 250);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.WHITE);
        g.drawString("Press R to Restart or Q to Quit", 270, 300);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (gameRunning) {
            switch (key) {
                case KeyEvent.VK_W:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, 0, -1));
                    break;
                case KeyEvent.VK_S:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, 0, 1));
                    break;
                case KeyEvent.VK_A:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, -1, 0));
                    break;
                case KeyEvent.VK_D:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.MOVE, 1, 0));
                    break;
                case KeyEvent.VK_1:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.CAST_SPELL, Spell.SpellType.FIRE));
                    spellCastSound.play();
                    break;
                case KeyEvent.VK_2:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.CAST_SPELL, Spell.SpellType.ICE));
                    spellCastSound.play();
                    break;
                case KeyEvent.VK_3:
                    client.sendAction(new PlayerAction(PlayerAction.ActionType.CAST_SPELL, Spell.SpellType.LIGHTNING));
                    spellCastSound.play();
                    break;
            }
        } else {
            if (key == KeyEvent.VK_R) {
                resetGame();
            } else if (key == KeyEvent.VK_Q) {
                System.exit(0);
            }
        }
    }

    private void resetGame() {
        player = new Player(100, 100);
        spells = new ArrayList<>();
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(300, 200, 100, 100, Color.RED));
        gameRunning = true;
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            for (Spell spell : spells) {
                spell.move();
            }

            Iterator<Spell> spellIterator = spells.iterator();
            while (spellIterator.hasNext()) {
                Spell spell = spellIterator.next();
                for (Obstacle obstacle : obstacles) {
                    if (obstacle.collidesWith(spell)) {
                        spellIterator.remove();
                        collisionSound.play();
                        break;
                    }
                }
            }

            if (player.getHealth() <= 0) {
                gameOverSound.play();
                gameRunning = false;
            }
        }

        repaint();
    }
}
