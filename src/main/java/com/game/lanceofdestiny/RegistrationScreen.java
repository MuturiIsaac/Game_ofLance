package com.game.lanceofdestiny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private LanceOfDestiny game;

    public RegistrationScreen(LanceOfDestiny game) {
        this.game = game;
        setTitle("Registration");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(confirmPasswordField);
        add(new JLabel());
        add(registerButton);
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.equals(confirmPassword)) {
                // Call the DatabaseManager to register the user
                DatabaseManager databaseManager = game.getDatabaseManager();
                boolean isRegistered = databaseManager.registerUser(username, password);

                if (isRegistered) {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the registration screen
                } else {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, "Registration failed. Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(RegistrationScreen.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}