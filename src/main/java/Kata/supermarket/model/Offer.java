package Kata.supermarket.model;

public class Offer {

    SpecialOfferType offerType;
    private final Product product;
    double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.product = product;
        this.argument = argument;
    }

    public SpecialOfferType getOfferType() {
        return offerType;
    }

    public Product getProduct() {
        return product;
    }

    public double getArgument() {
        return argument;
    }
}