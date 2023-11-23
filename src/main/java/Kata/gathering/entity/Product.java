package Kata.gathering.entity;

import java.math.BigDecimal;

public enum Product {

    COKE("Coke 0.33L", new BigDecimal("2.50")),
    KITKAT("Kitkat", new BigDecimal("2.00")),
    REDBULL("Redbull 0.25L", new BigDecimal("3.50")),
    KOMBUCHA("Kombucha 0.50L", new BigDecimal("5.00")),
    WATER("Mineral water 1.0L", new BigDecimal("1.50")),
    MATUTANO_BOCA_BITS("Matutano Boca Bits 50g", new BigDecimal("2.00")),
    MATUTANO_FRITOS("Matutano Fritos 50g", new BigDecimal("2.00"));

    private final String name;
    private final BigDecimal price;

    Product(String name, BigDecimal price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}