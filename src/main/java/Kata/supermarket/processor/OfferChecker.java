package Kata.supermarket.processor;

import Kata.supermarket.model.Receipt;
import Kata.supermarket.model.Request;

public interface OfferChecker {

    void check(Receipt receipt, Request request);
}
