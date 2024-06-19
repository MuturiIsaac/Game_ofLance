package com.game.lanceofdestiny;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lance of Destiny");
        GameClient client = new GameClient();
        GamePanel panel = new GamePanel(client);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
