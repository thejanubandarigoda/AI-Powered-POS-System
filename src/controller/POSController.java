package controller;

import db.DBConnection;
import model.CartItem;
import view.POSView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class POSController {

    private POSView posView;
    private List<CartItem> cart; // To store items added to the bill
    private double grandTotal = 0.0;

    public POSController(POSView posView) {
        this.posView = posView;
        this.cart = new ArrayList<>();

        // Add action listeners to buttons
        this.posView.getBtnAdd().addActionListener(e -> addToCart());
        this.posView.getBtnClear().addActionListener(e -> clearCart());
        this.posView.getBtnCheckout().addActionListener(e -> processCheckout());
    }

    // Method to search product and add to cart table
    private void addToCart() {
        String itemCode = posView.getItemCode();
        String qtyStr = posView.getQuantity();

        if (itemCode.trim().isEmpty() || qtyStr.trim().isEmpty()) {
            posView.showMessage("Please enter Item Code and Quantity.");
            return;
        }

        try {
            int qty = Integer.parseInt(qtyStr);
            if (qty <= 0) {
                posView.showMessage("Quantity must be greater than 0.");
                return;
            }

            // Search product in Database
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM products WHERE item_code = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, itemCode);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("product_name");
                double price = rs.getDouble("unit_price");
                int stock = rs.getInt("stock_quantity");

                // Check if enough stock is available
                if (qty > stock) {
                    posView.showMessage("Not enough stock! Only " + stock + " items available.");
                    return;
                }

                // Calculate subtotal
                double subTotal = price * qty;

                // Add to our Cart List
                CartItem item = new CartItem(itemCode, name, qty, price, subTotal);
                cart.add(item);

                // Add to UI Table
                DefaultTableModel model = posView.getTableModel();
                Vector<String> row = new Vector<>();
                row.add(itemCode);
                row.add(name);
                row.add(String.format("%.2f", price));
                row.add(String.valueOf(qty));
                row.add(String.format("%.2f", subTotal));
                model.addRow(row);

                // Update Grand Total
                grandTotal += subTotal;
                posView.setGrandTotal(String.format("%.2f", grandTotal));

                // Clear input fields for the next item
                posView.clearInputs();

            } else {
                posView.showMessage("Product not found! Check the Item Code.");
            }

        } catch (NumberFormatException ex) {
            posView.showMessage("Quantity must be a valid number.");
        } catch (Exception ex) {
            ex.printStackTrace();
            posView.showMessage("Database Error: " + ex.getMessage());
        }
    }

    // Method to clear the cart
    private void clearCart() {
        cart.clear();
        posView.getTableModel().setRowCount(0);
        grandTotal = 0.0;
        posView.setGrandTotal("0.00");
        posView.setBalance("0.00");
    }

    // Method to process payment and save to database
    private void processCheckout() {
        if (cart.isEmpty()) {
            posView.showMessage("Cart is empty! Add items to checkout.");
            return;
        }

        String cashStr = posView.getCashPaid();
        if (cashStr.trim().isEmpty()) {
            posView.showMessage("Please enter the Cash Paid amount.");
            return;
        }

        try {
            double cashPaid = Double.parseDouble(cashStr);

            // Check if cash paid is enough
            if (cashPaid < grandTotal) {
                posView.showMessage("Insufficient cash! Grand Total is Rs. " + grandTotal);
                return;
            }

            // Calculate Balance
            double balance = cashPaid - grandTotal;
            posView.setBalance(String.format("%.2f", balance));

            // Save Transaction to Database
            Connection conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Generate Invoice No (e.g., INV-167890)
            String invoiceNo = "INV-" + System.currentTimeMillis();
            String dateNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // 2. Insert into Sales Table
            String sqlSales = "INSERT INTO sales (invoice_no, sale_date, total_amount, cashier_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstSales = conn.prepareStatement(sqlSales);
            pstSales.setString(1, invoiceNo);
            pstSales.setString(2, dateNow);
            pstSales.setDouble(3, grandTotal);
            pstSales.setInt(4, 1); // For now, assuming Admin user_id is 1
            pstSales.executeUpdate();

            // 3. Insert into Sales_Items Table & Update Products Stock
            String sqlItems = "INSERT INTO sales_items (invoice_no, item_code, quantity, sub_total) VALUES (?, ?, ?, ?)";
            String sqlStock = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE item_code = ?";

            PreparedStatement pstItems = conn.prepareStatement(sqlItems);
            PreparedStatement pstStock = conn.prepareStatement(sqlStock);

            for (CartItem item : cart) {
                // Save item details
                pstItems.setString(1, invoiceNo);
                pstItems.setString(2, item.getItemCode());
                pstItems.setInt(3, item.getQuantity());
                pstItems.setDouble(4, item.getSubTotal());
                pstItems.executeUpdate();

                //  Reduce stock
                pstStock.setInt(1, item.getQuantity());
                pstStock.setString(2, item.getItemCode());
                pstStock.executeUpdate();
            }

            conn.commit(); // Save all changes
            conn.setAutoCommit(true);

            // Show Success Message
            posView.showMessage("Payment Successful!\nInvoice No: " + invoiceNo + "\nBalance: Rs. " + String.format("%.2f", balance));

            // Clear cart for the next customer
            clearCart();

        } catch (NumberFormatException ex) {
            posView.showMessage("Cash Paid must be a valid number.");
        } catch (Exception ex) {
            ex.printStackTrace();
            posView.showMessage("Checkout Error: " + ex.getMessage());
        }
    }
}