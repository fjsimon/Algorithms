package Kata.supermarket;

import Kata.supermarket.model.Product;

public interface SupermarketCatalog {

    void addProduct(Product product, double price);
    double getUnitPrice(Product product);
}
