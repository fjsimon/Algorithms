package Kata.gathering.states;

import Kata.gathering.VendingMachine;

import java.math.BigDecimal;

public class ReturnMoneyState implements VendingMachineState {

    private final BigDecimal returnAmount;

    public ReturnMoneyState(BigDecimal returnAmount) {

        this.returnAmount = returnAmount;
    }

    @Override
    public void proceed(VendingMachine vendingMachine) {
        vendingMachine.getWallet().removeValueInCoins(returnAmount);
        vendingMachine.display("Please take back your money, come back soon\n");
        vendingMachine.setState(new ProductSelectState()).proceed();
    }
}