package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    private String loggedUserRole;
    private String loggedUserName;

    // Menu Buttons
    private JButton btnPOS;
    private JButton btnInventory;
    private JButton btnAIInsights;
    private JButton btnReports;
    private JButton btnBackup;
    private JButton btnLogout;

    //  Main content panel
    private JPanel contentPanel;

    public DashboardView(String role, String username) {
        this.loggedUserRole = role;
        this.loggedUserName = username;

        // Window settings (Make it large to look like a full-screen app)
        setTitle("Sonata AI-POS System - Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. Top Header Panel ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185)); // Blue color
        headerPanel.setPreferredSize(new Dimension(1000, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblTitle = new JLabel("Sonata POS System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblUserInfo = new JLabel("Welcome, " + loggedUserName + " (" + loggedUserRole + ")");
        lblUserInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUserInfo.setForeground(Color.WHITE);

        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(lblUserInfo, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // --- 2. Left Sidebar Menu ---
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Grid for 7 buttons
        sidebarPanel.setBackground(new Color(44, 62, 80)); // Dark blue/gray color
        sidebarPanel.setPreferredSize(new Dimension(220, 600));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Initialize Buttons
        btnPOS = createMenuButton("🛒 POS & Billing");
        btnInventory = createMenuButton("📦 Inventory");
        btnAIInsights = createMenuButton("🧠 AI Insights");
        btnReports = createMenuButton("📊 Reports");
        btnBackup = createMenuButton("💾 Backup Data");
        btnLogout = createMenuButton("🚪 Logout");
        btnLogout.setBackground(new Color(231, 76, 60)); // Red color for Logout

        // Add buttons to Sidebar
        sidebarPanel.add(btnPOS);
        sidebarPanel.add(btnInventory);
        sidebarPanel.add(btnReports);

        // Authorization: Show AI Insights and Backup only if the user is an Admin
        if (loggedUserRole.equalsIgnoreCase("Admin")) {
            sidebarPanel.add(btnAIInsights);
            sidebarPanel.add(btnBackup);
        }

        // Add an empty space and put Logout at the bottom
        sidebarPanel.add(new JLabel(""));
        sidebarPanel.add(btnLogout);

        add(sidebarPanel, BorderLayout.WEST);

        // --- 3. Main Content Area ---
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Please select an option from the menu.", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.ITALIC, 18));
        lblWelcome.setForeground(Color.GRAY);
        contentPanel.add(lblWelcome, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    // A reusable method to design buttons (reduces code duplication)
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    // --- Getters for Controllers to access the buttons ---
    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnInventory() { return btnInventory; }
    public JButton getBtnPOS() { return btnPOS; }
    public JButton getBtnAIInsights() { return btnAIInsights; }
    public JButton getBtnReports() { return btnReports; }
    public JButton getBtnBackup() { return btnBackup; }

    // Method to get the main content panel so we can change its inner views
    public JPanel getContentPanel() {
        return contentPanel;
    }
}