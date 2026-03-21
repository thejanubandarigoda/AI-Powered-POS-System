package controller;

import view.DashboardView;
import view.InventoryView;
import view.LoginView;
import view.POSView;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController {

    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;

        // 1. POS & Billing Button Click Event (NEW)
        this.dashboardView.getBtnPOS().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPOSPanel();
            }
        });

        // 2. Inventory Button Click Event
        this.dashboardView.getBtnInventory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInventoryPanel();
            }
        });

        // 3. Logout Button Click Event
        this.dashboardView.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutSystem();
            }
        });
    }

    // --- Methods to Open Different Panels ---

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