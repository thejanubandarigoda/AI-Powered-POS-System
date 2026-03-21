package controller;

import view.DashboardView;
import view.InventoryView;
import view.LoginView;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController {

    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;

        // 1. Inventory Button Click Event
        this.dashboardView.getBtnInventory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInventoryPanel();
            }
        });

        // 2. Logout Button Click Event
        this.dashboardView.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutSystem();
            }
        });
    }

    // Method to open the Inventory View inside the Dashboard
    private void openInventoryPanel() {
        // Create the view and its controller
        InventoryView inventoryView = new InventoryView();
        InventoryController inventoryController = new InventoryController(inventoryView);

        // Get the center panel from Dashboard
        JPanel contentPanel = dashboardView.getContentPanel();

        // Remove old screens and add the new Inventory View
        contentPanel.removeAll();
        contentPanel.add(inventoryView, BorderLayout.CENTER);

        // Refresh the UI to show changes
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Method to handle user logout
    private void logoutSystem() {
        dashboardView.dispose(); // Close Dashboard

        // Open Login Screen again
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.setVisible(true);
    }
}