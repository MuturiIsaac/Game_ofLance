package com.game.lanceofdestiny;

import javax.swing.JFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameClient {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private GamePanel gamePanel;

    public GameClient() {
        try (Socket socket = new Socket(HOST, PORT)) {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            JFrame frame = new JFrame("Lance of Destiny");
            gamePanel = new GamePanel(this);
            frame.add(gamePanel);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            new Thread(new ServerListener()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    PlayerAction action = (PlayerAction) in.readObject();
                    // Update game state based on the received action
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendAction(PlayerAction action) {
        try {
            out.writeObject(action);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GameClient();
    }
}
