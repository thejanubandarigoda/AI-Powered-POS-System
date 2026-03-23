package controller;

import view.DashboardView;
import view.InventoryView;
import view.LoginView;
import view.POSView;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class DashboardController {

    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;

        // 1. POS & Billing Button Click Event (Using Lambda Expression)
        this.dashboardView.getBtnPOS().addActionListener(e -> openPOSPanel());

        // 2. Inventory Button Click Event
        this.dashboardView.getBtnInventory().addActionListener(e -> openInventoryPanel());

        // 3. Reports Button Click Event
        this.dashboardView.getBtnReports().addActionListener(e -> generateReports());

        // 4. Logout Button Click Event
        this.dashboardView.getBtnLogout().addActionListener(e -> logoutSystem());
    }

    // --- Methods to Open Different Panels & Actions ---

    // Open POS View
    private void openPOSPanel() {
        POSView posView = new POSView();
        POSController posController = new POSController(posView);
        changeContentPanel(posView);
    }

    // Open Inventory View
    private void openInventoryPanel() {
        InventoryView inventoryView = new InventoryView();
        InventoryController inventoryController = new InventoryController(inventoryView);
        changeContentPanel(inventoryView);
    }

    // Generate Reports using Jasper
    private void generateReports() {
        // Calls the ReportController to generate and display the Jasper Report
        ReportController.generateDailySalesReport();
    }

    // Helper method to change the center panel smoothly
    private void changeContentPanel(JPanel newPanel) {
        JPanel contentPanel = dashboardView.getContentPanel();
        contentPanel.removeAll();
        contentPanel.add(newPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Handle Logout
    private void logoutSystem() {
        dashboardView.dispose(); // Close Dashboard

        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.setVisible(true);
    }
}