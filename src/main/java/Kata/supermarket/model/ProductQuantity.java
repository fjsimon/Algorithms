package Kata.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductQuantity {

    private final Product product;
    private final double quantity;
}
