package Kata.gathering.entity;

import java.util.Stack;

public class ProductStack extends Stack<Product> {

    public static ProductStack of(Product product, int count) {

        ProductStack productStack = new ProductStack(product);
        for (int i = 0; i < count; i++) {
            productStack.push(product);
        }
        return productStack;
    }

    private final Product product;

    public ProductStack(Product product) {

        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
