package Kata.SupermarketCheckout.processor;

import Kata.SupermarketCheckout.model.Receipt;
import Kata.SupermarketCheckout.model.Request;

public interface OfferChecker {

    void check(Receipt receipt, Request request);
}
