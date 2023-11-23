package Kata.gathering.states;

import Kata.gathering.VendingMachine;
import Kata.gathering.entity.Product;
import Kata.gathering.exceptions.NoChangeException;

import java.math.BigDecimal;

public class ChangeCheckState implements VendingMachineState {

    private final BigDecimal insertedAmount;
    private final int selectedShelfNumber;

    public ChangeCheckState(BigDecimal insertedAmount, int selectedShelfNumber) {

        this.insertedAmount = insertedAmount;
        this.selectedShelfNumber = selectedShelfNumber;
    }

    @Override
    public void proceed(VendingMachine vendingMachine) {
        Product selectedProduct = vendingMachine.getProductInfo(selectedShelfNumber)
                .orElseThrow(() -> new IllegalStateException("Product not found on shelf number " + selectedShelfNumber));

        BigDecimal changeValue = insertedAmount.subtract(selectedProduct.getPrice());
        if (vendingMachine.getWallet().calculator().hasMoneyMapForValue(changeValue)) {
            vendingMachine.getWallet().removeValueInCoins(changeValue);
            vendingMachine.display("Please take your change %s PLN\n", changeValue);
            vendingMachine.setState(new ProvideProductState(selectedShelfNumber)).proceed();
        } else {
            vendingMachine.setState(new ReturnMoneyState(insertedAmount));
            throw new NoChangeException("Not enough coins for change\n");
        }
    }
}