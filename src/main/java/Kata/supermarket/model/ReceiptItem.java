package Kata.supermarket.model;
public class ReceiptItem {

    private final Product product;
    private final double price;
    private double totalPrice;
    private final double quantity;

    public ReceiptItem(Product product, double price, double totalPrice, double quantity) {
        this.product = product;
        this.price = price;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getQuantity() {
        return quantity;
    }
}