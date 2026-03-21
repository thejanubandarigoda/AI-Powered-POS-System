package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Note: We extend JPanel so this can fit inside the Main Dashboard
public class InventoryView extends JPanel {

    // Form Fields
    private JTextField txtItemCode;
    private JTextField txtProductName;
    private JTextField txtUnitPrice;
    private JTextField txtStock;

    // Buttons
    private JButton btnSave;
    private JButton btnClear;

    // Table components
    private JTable productTable;
    private DefaultTableModel tableModel;

    public InventoryView() {
        // Main layout for the Inventory Panel
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ---  Top: Title ---
        JLabel lblTitle = new JLabel("Inventory Management");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        // --- Left: Input Form ---
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 20));
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(300, 0));

        formPanel.add(new JLabel("Item Code (Barcode):"));
        txtItemCode = new JTextField();
        formPanel.add(txtItemCode);

        formPanel.add(new JLabel("Product Name:"));
        txtProductName = new JTextField();
        formPanel.add(txtProductName);

        formPanel.add(new JLabel("Unit Price (Rs):"));
        txtUnitPrice = new JTextField();
        formPanel.add(txtUnitPrice);

        formPanel.add(new JLabel("Stock Quantity:"));
        txtStock = new JTextField();
        formPanel.add(txtStock);

        // Buttons Panel inside the form
        btnSave = new JButton("Save Product");
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.WHITE);

        btnClear = new JButton("Clear Form");
        btnClear.setBackground(new Color(231, 76, 60));
        btnClear.setForeground(Color.WHITE);

        formPanel.add(btnSave);
        formPanel.add(btnClear);

        add(formPanel, BorderLayout.WEST);

        // --- Right: Data Table ---
        // Setup table columns
        String[] columns = {"Item Code", "Product Name", "Unit Price", "Stock Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        productTable = new JTable(tableModel);
        productTable.setRowHeight(25);
        productTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Getters for form values
    public String getItemCode() { return txtItemCode.getText(); }
    public String getProductName() { return txtProductName.getText(); }
    public String getUnitPrice() { return txtUnitPrice.getText(); }
    public String getStock() { return txtStock.getText(); }

    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnClear() { return btnClear; }
    public DefaultTableModel getTableModel() { return tableModel; }

    // Method to clear text fields
    public void clearFields() {
        txtItemCode.setText("");
        txtProductName.setText("");
        txtUnitPrice.setText("");
        txtStock.setText("");
        txtItemCode.requestFocus();
    }

    // Method to show messages
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}