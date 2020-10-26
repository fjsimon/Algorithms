package Kata.SupermarketCheckout.processor;

import Kata.SupermarketCheckout.model.*;

public class ThreeForTwoOfferChecker implements OfferChecker {

    @Override
    public void check(Receipt receipt, Request request) {

        Offer offer = request.getOffer();
        double unitPrice = request.getUnitPrice();
        double quantity = request.getQuantity();
        int quantityAsInt = (int) request.getQuantity();

        if (offer.getOfferType() == SpecialOfferType.ThreeForTwo) {
            double discountTotal = quantity * unitPrice - (( (quantityAsInt / 3) * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            receipt.addDiscount(new Discount(offer.getProduct(),"3 for 2", -discountTotal));
        }
    }
}
