package Kata.gathering.states;

import Kata.gathering.VendingMachine;

public interface VendingMachineState {

    void proceed(VendingMachine vendingMachine);
}