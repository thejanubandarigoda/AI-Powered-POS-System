package model;

public class CartItem {

    // Private variables for encapsulation
    private String itemCode;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subTotal;

    // Constructor
    public CartItem(String itemCode, String productName, int quantity, double unitPrice, double subTotal) {
        this.itemCode = itemCode;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    // Getters
    public String getItemCode() { return itemCode; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getSubTotal() { return subTotal; }

    // Setters
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
}