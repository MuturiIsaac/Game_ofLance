package com.game.lanceofdestiny;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Starting Main Game");
            JFrame frame = new JFrame("Multiplayer Game");
            System.out.println("JFrame created");

            try {
                GameClient client = new GameClient();
                System.out.println("GameClient created");

                StartScreenPanel startScreen = new StartScreenPanel();
                System.out.println("StartScreenPanel created");

                frame.add(startScreen);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                System.out.println("Start screen is visible");

                while (!startScreen.isStartGame()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Starting game panel");

                frame.remove(startScreen);
                GamePanel gamePanel = new GamePanel(client);
                frame.add(gamePanel);
                frame.revalidate();
                System.out.println("Game panel added");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
