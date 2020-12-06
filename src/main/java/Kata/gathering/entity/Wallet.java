package Kata.gathering.entity;

import Kata.gathering.delegate.MoneyDelegate;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class Wallet {

    private static final int EMPTY = 0;

    private final Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);

    public Wallet() {
        Stream.of(Money.values()).forEach(c -> coinCountMap.put(c, EMPTY));
    }

    public MoneyDelegate calculator() {

        return new MoneyDelegate(coinCountMap);
    }

    public boolean hasCoin(Money coin) {

        return coinCountMap.get(coin) > EMPTY;
    }

    public Wallet putCoin(Money coin) {

        return putCoin(coin, 1);
    }

    private Wallet putCoin(Money coin, int quantity) {

        coinCountMap.compute(coin, (c, v) -> v + quantity);
        return this;
    }

    public Map<Money, Integer> removeValueInCoins(BigDecimal value) {

        Map<Money, Integer> coinsToRemove = calculator().getMoneyMapForValue(value);
        if (coinsToRemove.isEmpty()) {
            throw new IllegalStateException("Not enough coins in wallet for value " + value);
        }

        coinsToRemove.forEach(this::removeCoins);
        return coinsToRemove;
    }

    private void removeCoins(Money coin, Integer count) {

        coinCountMap.compute(coin, (c, q) -> q - count);
    }
}