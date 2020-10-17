package Kata.SupermarketCheckout;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Offer {

    SpecialOfferType offerType;
    private final Product product;
    double argument;
}