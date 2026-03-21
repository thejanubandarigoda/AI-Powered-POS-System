package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Extend JPanel to fit inside the Main Dashboard
public class POSView extends JPanel {

    // Inputs
    private JTextField txtItemCode;
    private JTextField txtQuantity;
    private JTextField txtCashPaid;

    // Buttons
    private JButton btnAdd;
    private JButton btnCheckout;
    private JButton btnClear;

    // Table and Labels
    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel lblGrandTotal;
    private JLabel lblBalance;

    public POSView() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Top: Input Section ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createTitledBorder("Scan Product / Enter Item Code"));

        topPanel.add(new JLabel("Item Code:"));
        txtItemCode = new JTextField(12);
        txtItemCode.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(txtItemCode);

        topPanel.add(new JLabel("Qty:"));
        txtQuantity = new JTextField("1", 5); // Default quantity is 1
        txtQuantity.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(txtQuantity);

        btnAdd = new JButton("Add to Bill");
        btnAdd.setBackground(new Color(52, 152, 219)); // Light blue
        btnAdd.setForeground(Color.WHITE);
        topPanel.add(btnAdd);

        add(topPanel, BorderLayout.NORTH);

        // --- Center: Cart Table ---
        String[] columns = {"Item Code", "Product Name", "Unit Price", "Quantity", "Sub Total"};
        tableModel = new DefaultTableModel(columns, 0);
        cartTable = new JTable(tableModel);
        cartTable.setRowHeight(30);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 14));
        cartTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(cartTable);
        add(scrollPane, BorderLayout.CENTER);

        // --- Bottom: Payment and Checkout Section ---
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Total display area
        JPanel totalsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        totalsPanel.setBackground(Color.WHITE);

        JLabel lblTotalText = new JLabel("Grand Total (Rs): ", SwingConstants.RIGHT);
        lblTotalText.setFont(new Font("Arial", Font.BOLD, 20));
        lblGrandTotal = new JLabel("0.00", SwingConstants.LEFT);
        lblGrandTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblGrandTotal.setForeground(Color.RED);

        totalsPanel.add(lblTotalText);
        totalsPanel.add(lblGrandTotal);

        totalsPanel.add(new JLabel("Cash Paid (Rs): ", SwingConstants.RIGHT));
        txtCashPaid = new JTextField(10);
        txtCashPaid.setFont(new Font("Arial", Font.BOLD, 18));
        totalsPanel.add(txtCashPaid);

        totalsPanel.add(new JLabel("Balance (Rs): ", SwingConstants.RIGHT));
        lblBalance = new JLabel("0.00", SwingConstants.LEFT);
        lblBalance.setFont(new Font("Arial", Font.BOLD, 20));
        lblBalance.setForeground(new Color(46, 204, 113)); // Green

        totalsPanel.add(lblBalance);

        bottomPanel.add(totalsPanel, BorderLayout.WEST);

        // Buttons area
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        actionPanel.setBackground(Color.WHITE);

        btnClear = new JButton("Clear Cart");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBackground(new Color(231, 76, 60)); // Red
        btnClear.setForeground(Color.WHITE);

        btnCheckout = new JButton("Checkout & Print Bill");
        btnCheckout.setFont(new Font("Arial", Font.BOLD, 16));
        btnCheckout.setBackground(new Color(46, 204, 113)); // Green
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setPreferredSize(new Dimension(200, 50));

        actionPanel.add(btnClear);
        actionPanel.add(btnCheckout);

        bottomPanel.add(actionPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters for the Controller
    public String getItemCode() { return txtItemCode.getText(); }
    public String getQuantity() { return txtQuantity.getText(); }
    public String getCashPaid() { return txtCashPaid.getText(); }

    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnCheckout() { return btnCheckout; }
    public JButton getBtnClear() { return btnClear; }

    public DefaultTableModel getTableModel() { return tableModel; }

    // Setters to update UI from Controller
    public void setGrandTotal(String total) { lblGrandTotal.setText(total); }
    public void setBalance(String balance) { lblBalance.setText(balance); }

    // Method to clear inputs after adding an item
    public void clearInputs() {
        txtItemCode.setText("");
        txtQuantity.setText("1");
        txtItemCode.requestFocus();
    }

    // Method to show messages
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}