package com.game.lanceofdestiny;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameServer {
    private static final int PORT = 23456;
    private List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private List<Player> players = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new GameServer().startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private Player player;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                player = new Player(100, 100); // Initialize player with default position
                players.add(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object input = in.readObject();
                    if (input instanceof PlayerAction) {
                        handleAction((PlayerAction) input);
                    }
                    out.writeObject(players); // Send updated player list to the client
                    out.flush();
                }
            } catch (EOFException e) {
                System.out.println("Client disconnected");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clients.remove(this);
                players.remove(player);
            }
        }

        private void handleAction(PlayerAction action) {
            switch (action.getActionType()) {
                case MOVE:
                    // Update player position based on the action
                    player.move(action.getX(), action.getY());
                    break;
                case CAST_SPELL:
                    // Handle spell casting
                    break;
            }
        }
    }
}
