package view;

import javax.swing.*;
import java.awt.*;

public class AIInsightsView extends JPanel {

    private JButton btnGenerate;
    private JTextArea txtInsights;

    public AIInsightsView() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Top: Title ---
        JLabel lblTitle = new JLabel("🧠 AI Business Insights");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(142, 68, 173)); // Purple color
        add(lblTitle, BorderLayout.NORTH);

        // --- Center: Text Area for AI Output ---
        txtInsights = new JTextArea();
        txtInsights.setText("Click the 'Generate AI Insights' button below to analyze your recent sales data using Local AI...");
        txtInsights.setFont(new Font("Arial", Font.PLAIN, 16));
        txtInsights.setLineWrap(true);
        txtInsights.setWrapStyleWord(true);
        txtInsights.setEditable(false); // User cannot edit the AI output
        txtInsights.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(txtInsights);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        add(scrollPane, BorderLayout.CENTER);

        // --- Bottom: Action Button ---
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        btnGenerate = new JButton("Generate AI Insights");
        btnGenerate.setFont(new Font("Arial", Font.BOLD, 16));
        btnGenerate.setBackground(new Color(155, 89, 182)); // Purple color
        btnGenerate.setForeground(Color.WHITE);
        btnGenerate.setPreferredSize(new Dimension(250, 45));
        btnGenerate.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(btnGenerate);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters and Setters
    public JButton getBtnGenerate() { return btnGenerate; }

    public void setInsightsText(String text) {
        txtInsights.setText(text);
    }
}