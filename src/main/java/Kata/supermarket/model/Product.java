package Kata.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final String name;
    private final ProductUnit unit;
}
