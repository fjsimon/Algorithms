package Kata.gathering.delegate;

import Kata.gathering.entity.Money;

import java.math.BigDecimal;
import java.util.*;

public class MoneyDelegate {

    private final Map<Money, Integer> availableMoneyMap;

    public MoneyDelegate(Map<Money, Integer> availableMoneyMap) {

        this.availableMoneyMap = new EnumMap<>(availableMoneyMap);
    }

    public boolean hasMoneyMapForValue(BigDecimal value) {

        return !getMoneyMapForValue(value).isEmpty();
    }

    public Map<Money, Integer> getMoneyMapForValue(BigDecimal value) {

        Map<Money, Integer> coinCountMap = getMoneyMap(value);
        return getMoneyMapValue(coinCountMap).compareTo(value) == 0 ? coinCountMap : Collections.emptyMap();
    }

    public BigDecimal getMoneyMapValue() {

        return getMoneyMapValue(availableMoneyMap);
    }

    private Map<Money, Integer> getMoneyMap(BigDecimal value) {
        BigDecimal remainingAmount = value;
        Map<Money, Integer> moneyCountMap = new EnumMap<>(Money.class);
        for (Money currentMoney : availableMoneyMap.keySet()) {
            BigDecimal currentValue = currentMoney.getMoneyValue();
            if (remainingAmount.compareTo(currentValue) >= 0) {

                final int availableCoins = availableMoneyMap.get(currentMoney);
                final int maxNeededCoins = remainingAmount.divideToIntegralValue(currentValue).intValue();
                final int usedCoins = availableCoins > maxNeededCoins ? maxNeededCoins : availableCoins;

                moneyCountMap.put(currentMoney, usedCoins);
                remainingAmount = remainingAmount.subtract(currentValue.multiply(BigDecimal.valueOf(usedCoins)));
            }
        }
        return moneyCountMap;
    }

    private BigDecimal getMoneyMapValue(Map<Money, Integer> coinCountMap) {

        return coinCountMap.entrySet().stream()
                .map(this::getEntryValue)
                .reduce(BigDecimal::add)
                .get();
    }

    private BigDecimal getEntryValue(Map.Entry<Money, Integer> entry) {

        return entry.getKey().getMoneyValue().multiply(BigDecimal.valueOf(entry.getValue()));
    }
}