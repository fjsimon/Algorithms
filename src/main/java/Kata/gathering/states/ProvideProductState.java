package Kata.gathering.states;

import Kata.gathering.VendingMachine;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProvideProductState implements VendingMachineState {

    private final int selectedShelfNumber;

    @Override
    public void proceed(VendingMachine vendingMachine) {
        vendingMachine.popProduct(selectedShelfNumber);
        vendingMachine.display("Take your product, thank you for purchase\n");
        vendingMachine.setState(new ProductSelectState()).proceed();
    }
}