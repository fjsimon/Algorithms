package Kata.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Discount {

    private final Product product;
    private final String description;
    private final double discountAmount;
}
