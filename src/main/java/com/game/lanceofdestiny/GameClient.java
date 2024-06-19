package com.game.lanceofdestiny;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GameClient {
    private static final String HOST = "localhost";
    private static final int PORT = 23456;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    public GameClient() {
        try {
            socket = new Socket(HOST, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connected to server");

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
                    Object input = in.readObject();
                    if (input instanceof List) {
                        System.out.println("Received update from server");
                        // Update game state with the list of players
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Rest of the code...


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
