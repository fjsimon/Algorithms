package Kata.supermarket.model;

public class Discount {

    private final Product product;
    private final String description;
    private final double discountAmount;

    public Discount(Product product, String description, double discountAmount) {

        this.product = product;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public Product getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
