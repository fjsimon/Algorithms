package Kata.SupermarketCheckout.processor;

import Kata.SupermarketCheckout.Receipt;
import Kata.SupermarketCheckout.Request;

public interface OfferChecker {

    void check(Receipt receipt, Request request);
}
