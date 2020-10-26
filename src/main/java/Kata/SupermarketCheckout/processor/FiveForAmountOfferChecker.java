package Kata.SupermarketCheckout.processor;

import Kata.SupermarketCheckout.model.*;

public class FiveForAmountOfferChecker implements OfferChecker {

    @Override
    public void check(Receipt receipt, Request request) {

        Offer offer = request.getOffer();
        double unitPrice = request.getUnitPrice();
        double quantity = request.getQuantity();
        int quantityAsInt = (int) request.getQuantity();

        if (offer.getOfferType() == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {

            double discountTotal = quantity * unitPrice - (offer.getArgument() * (quantityAsInt / 5) + quantityAsInt % 5 * unitPrice);
            receipt.addDiscount(new Discount(offer.getProduct(),"5 for " + offer.getArgument(), -discountTotal));
        }
    }
}
