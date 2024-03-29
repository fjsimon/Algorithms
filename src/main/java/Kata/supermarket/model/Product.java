package Kata.supermarket.model;

public class Product {

    private final String name;
    private final ProductUnit unit;

    public Product(String name, ProductUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public ProductUnit getUnit() {
        return unit;
    }
}
