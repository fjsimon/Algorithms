package Kata.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiptItem {

    private final Product product;
    private final double price;
    private double totalPrice;
    private final double quantity;
}