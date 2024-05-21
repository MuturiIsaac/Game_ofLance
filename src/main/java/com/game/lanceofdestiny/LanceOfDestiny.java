package com.game.lanceofdestiny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LanceOfDestiny extends JFrame implements Runnable, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Lance of Destiny";
    private boolean isRunning;
    private Thread gameThread;
    private GamePanel gamePanel;
    private BuildingModePanel buildingModePanel;
    private RunningModePanel runningModePanel;
    private JPanel currentPanel;
    private boolean isInBuildingMode;
    private String username;
    private DatabaseManager databaseManager;

    public LanceOfDestiny() {
        initializeComponents();
        showLoginScreen();
    }

    private void initializeComponents() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(this);

        gamePanel = new GamePanel();
        buildingModePanel = new BuildingModePanel();
        runningModePanel = new RunningModePanel();
        currentPanel = buildingModePanel;
        isInBuildingMode = true;

        databaseManager = new DatabaseManager();

        add(currentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    // Inside the initializeComponents() method
    initializeComponents() {
        // ...
        // After creating the databaseManager instance
        showLoginScreen();
    }

    private void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(this);
        loginScreen.setVisible(true);
    }

    public void startGame(String username) {
        this.username = username;
        loadGameState();
        start();
    }

    private void loadGameState() {
        String gameData = databaseManager.loadGameState(username);
        if (gameData != null) {
            GameState loadedGameState = GameState.fromJSON(gameData);
            // Load the game state from the loaded object
        } else {
            // Start a new game
        }
    }

    public void saveGameState() {
        GameState currentGameState = new GameState(...); // Create a GameState object with the current game state
        String gameDataJson = currentGameState.toJSON();
        databaseManager.saveGameState(username, gameDataJson);
    }

    // Other methods (start, stop, run, update, render, switchToRunningMode, switchToBuildingMode, etc.)

    public void start() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        isRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double targetFPS = 60.0;
        double nsPerFrame = 1000000000.0 / targetFPS;
        while (isRunning) {
            long currentTime = System.nanoTime();
            double elapsedTime = currentTime - lastTime;
            if (elapsedTime >= nsPerFrame) {
                lastTime = currentTime;
                update();
                render();
            }
        }
    }

    private void update() {
        // Update game logic here
    }

    private void render() {
        gamePanel.repaint();
    }

    public static void main(String[] args) {
        LanceOfDestiny game = new LanceOfDestiny();
        game.start();
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Render game objects here
        }
    }

    private void switchToRunningMode() {
        remove(currentPanel);
        currentPanel = runningModePanel;
        add(currentPanel, BorderLayout.CENTER);
        isInBuildingMode = false;
        revalidate();
        repaint();
    }

    private void switchToBuildingMode() {
        remove(currentPanel);
        currentPanel = buildingModePanel;
        add(currentPanel, BorderLayout.CENTER);
        isInBuildingMode = true;
        revalidate();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_B) {
            if (isInBuildingMode) {
                switchToRunningMode();
            } else {
                switchToBuildingMode();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not implemented
    }
}