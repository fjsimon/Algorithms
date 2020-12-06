package Kata.gathering;

import Kata.gathering.entity.Product;
import Kata.gathering.entity.ProductStack;
import Kata.gathering.entity.Wallet;
import Kata.gathering.exceptions.ItemNotSelectedException;
import Kata.gathering.exceptions.NotFoundException;
import Kata.gathering.states.VendingMachineState;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Accessors(chain = true)
@RequiredArgsConstructor
public class VendingMachine {

    private final int shelfCount;

    @Delegate
    private Wallet wallet = new Wallet();

    @Setter
    private VendingMachineState state;

    private Map<Integer, ProductStack> productMap = new HashMap<>();

    public void start(VendingMachineState state) {

        setState(state).proceed();
    }

    public void proceed() {

        try {
            state.proceed(this);
        } catch (RuntimeException ex) {
            display(ex.getMessage());
            this.proceed();
        }
    }

    public VendingMachine putProductsOnShelf(int shelfNumber, Product product, int count) {

        if (!correctShelfNumber(shelfNumber)) {
            throw new IllegalArgumentException("Incorrect shelf number: " + shelfNumber);
        }

        productMap.put(shelfNumber, ProductStack.of(product, count));
        return this;
    }

    private boolean correctShelfNumber(int selectedShelfNumber) {

        return selectedShelfNumber >= 1 && selectedShelfNumber <= shelfCount;
    }

    private boolean checkProductAvailable(int selectedShelfNumber) {

        return !productMap.containsKey(selectedShelfNumber) || productMap.get(selectedShelfNumber).empty();
    }

    public int selectProductShelf() {

        final int selectedShelfNumber = readNumber();
        if (!correctShelfNumber(selectedShelfNumber)) {
            throw new ItemNotSelectedException("Incorrect shelf number! Try again!\n");
        }

        if (checkProductAvailable(selectedShelfNumber)) {
            throw new NotFoundException("Product out of stock, select other product!\n");
        }

        return selectedShelfNumber;
    }

    public void displayProducts() {

        display("Money in machine: %s PLN\n", wallet.calculator().getMoneyMapValue());

        for (int i = 1; i <= shelfCount; i++) {
            if (productMap.containsKey(i) && productMap.get(i).size() > 0) {
                ProductStack productStack = productMap.get(i);
                display("%s -> %s (%s PLN) x%s\n", i,
                        productStack.getName(),
                        productStack.getPrice(),
                        productStack.size());
            } else {
                display("%s -> empty\n", i);
            }
        }
    }

    public Optional<Product> getProductInfo(int shelfNumber) {

        if (productMap.containsKey(shelfNumber)) {
            return Optional.of(productMap.get(shelfNumber).getProduct());
        }
        return Optional.empty();
    }

    public void popProduct(int shelfNumber) {

        productMap.get(shelfNumber).pop();
    }

    public void display(String messageTemplate, Object... args) {

        System.out.print(String.format(messageTemplate, args));
    }

    private int readNumber() {

        String input = readInput();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private String readInput() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}