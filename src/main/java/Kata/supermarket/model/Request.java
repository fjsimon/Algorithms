package Kata.supermarket.model;

public class Request {

    Offer offer;
    double unitPrice;
    double quantity;

    // TODO Builder pattern in ShoppingCart
    public Request(Offer offer, double unitPrice, double quantity) {
        this.offer = offer;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Offer getOffer() {
        return offer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }
}
