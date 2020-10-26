package Kata.SupermarketCheckout;

import Kata.SupermarketCheckout.model.Product;

public interface SupermarketCatalog {

    void addProduct(Product product, double price);
    double getUnitPrice(Product product);
}
