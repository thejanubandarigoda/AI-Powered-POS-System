package controller;

import db.DBConnection;
import view.DashboardView;
import view.LoginView;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    private LoginView loginView;

    // Constructor to initialize the controller with the view
    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        // Add Action Listener to the login button
        this.loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
    }

    // Method to authenticate user against the database
    private void authenticateUser() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();

        // 1. Validation: Check if fields are empty
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            loginView.showMessage("Please enter both Username and Password!");
            return;
        }

        // 2. Database verification
        try {
            Connection conn = DBConnection.getInstance().getConnection();

            // SQL Query to check credentials
            String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // If credentials are valid, get user details
                String role = rs.getString("role");
                String loggedUser = rs.getString("username");

                // Show success message
                JOptionPane.showMessageDialog(loginView, "Login Successful!");

                // Close the current login window
                loginView.dispose();

                // Open the new main dashboard
                DashboardView dashboard = new DashboardView(role, loggedUser);

                // ---> THIS IS THE MISSING LINE: Initialize DashboardController <---
                DashboardController dashController = new DashboardController(dashboard);

                dashboard.setVisible(true);

            } else {
                // If credentials are invalid
                loginView.showMessage("Invalid Username or Password! Please try again.");
            }

        } catch (Exception ex) {
            // Exception Handling
            ex.printStackTrace();
            loginView.showMessage("Database Error: " + ex.getMessage());
        }
    }
}