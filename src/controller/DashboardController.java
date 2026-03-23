package controller;

import view.DashboardView;
import view.InventoryView;
import view.LoginView;
import view.POSView;
import view.AIInsightsView;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class DashboardController {

    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;

        // 1. POS & Billing Button Click Event
        this.dashboardView.getBtnPOS().addActionListener(e -> openPOSPanel());

        // 2. Inventory Button Click Event
        this.dashboardView.getBtnInventory().addActionListener(e -> openInventoryPanel());

        // 3. Reports Button Click Event
        this.dashboardView.getBtnReports().addActionListener(e -> generateReports());

        // 4. AI Insights Button Click Event
        this.dashboardView.getBtnAIInsights().addActionListener(e -> openAIPanel());

        // 5. Database Backup Button Click Event (අලුතින් එකතු කළා)
        this.dashboardView.getBtnBackup().addActionListener(e -> runDatabaseBackup());

        // 6. Logout Button Click Event
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

    // Open AI Insights View
    private void openAIPanel() {
        AIInsightsView aiView = new AIInsightsView();
        AIInsightsController aiController = new AIInsightsController(aiView);
        changeContentPanel(aiView);
    }

    // Database Backup එක පටන් ගන්න Method එක
    private void runDatabaseBackup() {
        // BackupController එකේ තියෙන static method එකට Dashboard එකේ frame එක යවනවා
        BackupController.backupDatabase(dashboardView);
    }

    // Generate Reports using Jasper
    private void generateReports() {
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