package Kata.supermarket.processor;

import Kata.supermarket.model.*;

public class TwoForAmountOfferChecker implements OfferChecker{

    @Override
    public void check(Receipt receipt, Request request) {

        Offer offer = request.getOffer();
        double unitPrice = request.getUnitPrice();
        double quantity = request.getQuantity();
        int quantityAsInt = (int) request.getQuantity();

        if (offer.getOfferType() == SpecialOfferType.TwoForAmount && quantityAsInt >= 2) {

            double discountTotal = quantity * unitPrice - (offer.getArgument() * (quantityAsInt / 2) + quantityAsInt % 2 * unitPrice);
            receipt.addDiscount(new Discount(offer.getProduct(),"2 for " + offer.getArgument(), -discountTotal));
        }

    }
}

