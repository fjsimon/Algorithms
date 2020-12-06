package Kata.gathering.states;

import Kata.gathering.VendingMachine;
import Kata.gathering.entity.Product;
import Kata.gathering.exceptions.NoChangeException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ChangeCheckState implements VendingMachineState {

    private final BigDecimal insertedAmount;
    private final int selectedShelfNumber;

    @Override
    public void proceed(VendingMachine vendingMachine) {
        Product selectedProduct = vendingMachine.getProductInfo(selectedShelfNumber)
                .orElseThrow(() -> new IllegalStateException("Product not found on shelf number " + selectedShelfNumber));

        BigDecimal changeValue = insertedAmount.subtract(selectedProduct.getPrice());
        if (vendingMachine.calculator().hasMoneyMapForValue(changeValue)) {
            vendingMachine.removeValueInCoins(changeValue);
            vendingMachine.display("Please take your change %s PLN\n", changeValue);
            vendingMachine.setState(new ProvideProductState(selectedShelfNumber)).proceed();
        } else {
            vendingMachine.setState(new ReturnMoneyState(insertedAmount));
            throw new NoChangeException("Not enough coins for change\n");
        }
    }
}