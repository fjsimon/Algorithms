package Kata.SupermarketCheckout.model;

import Kata.SupermarketCheckout.model.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final String name;
    private final ProductUnit unit;
}
