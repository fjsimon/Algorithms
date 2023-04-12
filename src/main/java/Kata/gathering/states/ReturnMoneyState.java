package Kata.gathering.states;

import Kata.gathering.VendingMachine;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ReturnMoneyState implements VendingMachineState {

    private final BigDecimal returnAmount;

    @Override
    public void proceed(VendingMachine vendingMachine) {
        vendingMachine.getWallet().removeValueInCoins(returnAmount);
        vendingMachine.display("Please take back your money, come back soon\n");
        vendingMachine.setState(new ProductSelectState()).proceed();
    }
}