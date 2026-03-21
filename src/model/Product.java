package model;

public class Product {
    // Encapsulation: Making variables private (Good OOP practice)
    private String itemCode;
    private String productName;
    private double unitPrice;
    private int stockQuantity;

    // Constructor to initialize a Product object
    public Product(String itemCode, String productName, double unitPrice, int stockQuantity) {
        this.itemCode = itemCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters to access private variables
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}