package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    // UI Components
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        // Basic Window Settings
        setTitle("Sonata POS - System Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Main Panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header Label
        JLabel lblHeader = new JLabel("Sonata AI-POS System", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setForeground(new Color(41, 128, 185)); // Blue color
        mainPanel.add(lblHeader, BorderLayout.NORTH);

        // Username and Password Form Section
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(txtPassword);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Login Button Panel
        JPanel btnPanel = new JPanel();
        btnLogin = new JButton("Login to System");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(46, 204, 113)); // Green color
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPanel.add(btnLogin);

        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);
    }

    // Getters for the Controller to access field values
    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    // Method to display messages/errors
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}