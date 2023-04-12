package Kata.gathering.delegate;

import Kata.gathering.entity.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Write assertions for MoneyDelegate")
class MoneyDelegateTest {

    @Test
    @DisplayName("hasCoinsForValue should return false when value greater than available")
    public void hasCoinsForValue_returns_false_for_value_greater_than_available() {
        // given
        Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);
        coinCountMap.put(Money.TWO_EUROS, 1);
        coinCountMap.put(Money.ONE_EURO, 5);
        coinCountMap.put(Money.FIFTY_CENTS, 7);
        coinCountMap.put(Money.ONE_CENT, 20);

        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("12")), is(false));
    }

    @Test
    @DisplayName("hasCoinsForValue should return false when no enough coins")
    public void hasCoinsForValue_returns_false_if_there_is_not_enough_coins() {
        // given
        Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);
        coinCountMap.put(Money.TWO_EUROS, 1);
        coinCountMap.put(Money.ONE_EURO, 1);
        coinCountMap.put(Money.FIFTY_CENTS, 1);

        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("1.20")), is(false));
        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("3.40")), is(false));
        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("0.80")), is(false));
    }


    @Test
    @DisplayName("hasCoinsForValue should return true when enough coins")
    public void hasCoinsForValue_returns_true_when_have_coins_for_value() {
        // given
        Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);
        coinCountMap.put(Money.TWO_EUROS, 1);
        coinCountMap.put(Money.ONE_EURO, 5);
        coinCountMap.put(Money.FIFTY_CENTS, 7);
        coinCountMap.put(Money.TEN_CENTS, 10);

        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("11.50")), is(true));
        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("11.10")), is(true));
        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("0.80")), is(true));
        assertThat(new MoneyDelegate(coinCountMap).hasMoneyMapForValue(new BigDecimal("1.70")), is(true));
    }


    @Test
    @DisplayName("getCoinsValue should calculate coins value")
    public void getCoinsValue_should_calculate_coins_value() {
        // given
        Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);
        coinCountMap.put(Money.TWO_EUROS, 1);
        coinCountMap.put(Money.ONE_EURO, 5);
        coinCountMap.put(Money.FIFTY_CENTS, 7);
        coinCountMap.put(Money.TEN_CENTS, 10);

        assertThat(new MoneyDelegate(coinCountMap).getMoneyMapValue(), is(new BigDecimal("11.5")));
    }

    @Test
    @DisplayName("getCoinsValue should return coins for passed value")
    public void getCoinsValue_should_return_coins_map_for_passed_value() {
        // given
        Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);
        coinCountMap.put(Money.TWO_EUROS, 1);
        coinCountMap.put(Money.ONE_EURO, 5);
        coinCountMap.put(Money.FIFTY_CENTS, 7);
        coinCountMap.put(Money.TEN_CENTS, 10);

        // when
        Map<Money, Integer> returnedCoins = new MoneyDelegate(coinCountMap)
                .getMoneyMapForValue(new BigDecimal("2.60"));

        assertThat(returnedCoins.size(), is(3));
        assertThat(returnedCoins.get(Money.TWO_EUROS), is(1));
        assertThat(returnedCoins.get(Money.FIFTY_CENTS), is(1));
        assertThat(returnedCoins.get(Money.TEN_CENTS), is(1));

        // then
        assertThat(new MoneyDelegate(returnedCoins).getMoneyMapValue(),  is(new BigDecimal("2.6")));

    }


    @Test
    @DisplayName("getCoinsValue should return empty when not enough coins")
    public void getCoinsForValue_should_return_empty_map_when_not_enough_coins_available() {
        // given
        Map<Money, Integer> coinCountMap = new EnumMap<>(Money.class);
        coinCountMap.put(Money.TWO_EUROS, 1);
        coinCountMap.put(Money.ONE_EURO, 1);
        coinCountMap.put(Money.FIFTY_CENTS, 2);

        // when
        Map<Money, Integer> returnedCoins = new MoneyDelegate(coinCountMap)
                .getMoneyMapForValue(new BigDecimal("1.30"));

        // then
        assertThat(returnedCoins.isEmpty(), is(true));
    }
}