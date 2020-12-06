package Kata.gathering.states;

import Kata.gathering.VendingMachine;
import Kata.gathering.entity.Money;
import Kata.gathering.entity.Product;
import Kata.gathering.exceptions.CoinNotAcceptedException;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class CoinsInsertState implements VendingMachineState {

    private static final String ABORT = "c";

    private final int selectedShelfNumber;
    private final Product selectedProduct;
    private BigDecimal insertedAmount = BigDecimal.ZERO;

    @Override
    public void proceed(VendingMachine vendingMachine) {

        vendingMachine.display("Selected: %s, insert: %s PLN\n",
                selectedProduct.getName(),
                selectedProduct.getPrice().subtract(insertedAmount));
        vendingMachine.display("Insert coin (or press 'c' to abort): ");

        String input = readInput();
        if (ABORT.equals(input) && insertedAmount.compareTo(BigDecimal.ZERO) > 0) {
            vendingMachine.setState(new ReturnMoneyState(insertedAmount)).proceed();
        } else if (ABORT.equals(input)) {
            vendingMachine.setState(new ProductSelectState()).proceed();
        }

        Optional<Money> insertedCoin = Money.fromDenomination(input);
        if (insertedCoin.isPresent()) {
            insertedAmount = insertedAmount.add(insertedCoin.get().getMoneyValue());
            vendingMachine.putCoin(insertedCoin.get());

            if (insertedAmount.compareTo(selectedProduct.getPrice()) == 0) {
                vendingMachine.setState(new ProvideProductState(selectedShelfNumber));
            } else if (insertedAmount.compareTo(selectedProduct.getPrice()) > 0) {
                vendingMachine.setState(new ChangeCheckState(insertedAmount, selectedShelfNumber));
            }

            vendingMachine.proceed();

        } else {
            throw new CoinNotAcceptedException("Incorrect input.\nAccepted denominations: 5, 2, 1, 0.5, 0.2, 0.1\n");
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