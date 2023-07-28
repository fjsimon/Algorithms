package Kata.gathering.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.Stack;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductStack extends Stack<Product> {

    public static ProductStack of(Product product, int count) {

        ProductStack productStack = new ProductStack(product);
        for (int i = 0; i < count; i++) {
            productStack.push(product);
        }
        return productStack;
    }

    @Delegate
    @Getter
    private final Product product;

}
