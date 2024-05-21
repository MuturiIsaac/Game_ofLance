package com.game.lanceofdestiny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private LanceOfDestiny game;

    public LoginScreen(LanceOfDestiny game) {
        this.game = game;
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Call the DatabaseManager to authenticate the user
            DatabaseManager databaseManager = game.getDatabaseManager();
            boolean isAuthenticated = databaseManager.authenticateUser(username, password);

            if (isAuthenticated) {
                // Start the game with the authenticated user
                game.startGame(username);
                dispose(); // Close the login screen
            } else {
                JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Inside the RegisterButtonListener class in LoginScreen
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show the registration screen
            RegistrationScreen registrationScreen = new RegistrationScreen(game);
            registrationScreen.setVisible(true);
        }
    }
}