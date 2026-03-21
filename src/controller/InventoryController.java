package controller;

import db.DBConnection;
import view.InventoryView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class InventoryController {

    private InventoryView inventoryView;

    // Constructor
    public InventoryController(InventoryView inventoryView) {
        this.inventoryView = inventoryView;

        // Load existing products from database to the table when opening
        loadTableData();

        // Action Listener for the Save Button
        this.inventoryView.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProduct();
            }
        });

        // Action Listener for the Clear Button
        this.inventoryView.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryView.clearFields();
            }
        });
    }

    // Method to save a new product
    private void saveProduct() {
        String itemCode = inventoryView.getItemCode();
        String productName = inventoryView.getProductName();
        String unitPriceStr = inventoryView.getUnitPrice();
        String stockStr = inventoryView.getStock();

        // 1. Validation: Check for empty fields
        if (itemCode.trim().isEmpty() || productName.trim().isEmpty() ||
                unitPriceStr.trim().isEmpty() || stockStr.trim().isEmpty()) {
            inventoryView.showMessage("All fields are required! Please fill them.");
            return;
        }

        try {
            // 2. Validation: Ensure price and stock are valid numbers
            double unitPrice = Double.parseDouble(unitPriceStr);
            int stockQuantity = Integer.parseInt(stockStr);

            // 3. Database Operation: Insert query
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO products (item_code, product_name, unit_price, stock_quantity) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, itemCode);
            pst.setString(2, productName);
            pst.setDouble(3, unitPrice);
            pst.setInt(4, stockQuantity);

            int result = pst.executeUpdate();

            if (result > 0) {
                inventoryView.showMessage("Product saved successfully!");
                inventoryView.clearFields(); // Clear text boxes
                loadTableData(); // Refresh the table to show the new item
            }

        } catch (NumberFormatException ex) {
            // Handle error if user types letters in price/stock fields
            inventoryView.showMessage("Unit Price and Stock Quantity must be valid numbers!");
        } catch (Exception ex) {
            // Handle database errors (e.g., duplicate item code)
            ex.printStackTrace();
            inventoryView.showMessage("Database Error: " + ex.getMessage());
        }
    }

    // Method to load data from database to the JTable
    private void loadTableData() {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Get the table model to add rows
            DefaultTableModel model = inventoryView.getTableModel();
            model.setRowCount(0); // Clear existing rows before loading

            // Loop through the result set and add data to table
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("item_code"));
                row.add(rs.getString("product_name"));
                row.add(String.valueOf(rs.getDouble("unit_price")));
                row.add(String.valueOf(rs.getInt("stock_quantity")));

                model.addRow(row); // Add row to table
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            inventoryView.showMessage("Failed to load table data: " + ex.getMessage());
        }
    }
}