package Kata.supermarket.processor;

import Kata.supermarket.model.*;

public class TenPercentDiscountOfferChecker implements OfferChecker {

    @Override
    public void check(Receipt receipt, Request request) {

        Offer offer = request.getOffer();
        double unitPrice = request.getUnitPrice();
        double quantity = request.getQuantity();

        if (offer.getOfferType() == SpecialOfferType.TenPercentDiscount) {

            double discountTotal = quantity * unitPrice * offer.getArgument() / 100.0;
            receipt.addDiscount(new Discount(offer.getProduct(), offer.getArgument() + "% off", -discountTotal));
        }
    }
}
