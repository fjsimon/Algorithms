package Kata.gathering.entity;

import Kata.gathering.delegate.MoneyDelegate;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WalletTest {

    @Test
    public void should_be_empty_after_construct() {
        // given
        Wallet wallet = new Wallet();
        // expect
        EnumSet.allOf(Money.class).forEach(c -> assertThat(wallet.hasCoin(c), is(false)));
    }

    @Test
    public void should_have_coin_after_put() {
        // given
        Wallet wallet = new Wallet().putCoin(Money.TWO_EUROS);
        // expect
        assertThat(wallet.hasCoin(Money.TWO_EUROS), is(true));
    }

    @Test
    public void should_remove_value_in_coins_from_wallet() {
        // given
        Wallet wallet = new Wallet()
                .putCoin(Money.FIVE_EUROS)
                .putCoin(Money.TWO_EUROS)
                .putCoin(Money.TWO_EUROS)
                .putCoin(Money.TWO_EUROS)
                .putCoin(Money.ONE_EURO)
                .putCoin(Money.ONE_EURO)
                .putCoin(Money.ONE_EURO)
                .putCoin(Money.TWENTY_CENTS)
                .putCoin(Money.TWENTY_CENTS)
                .putCoin(Money.TEN_CENTS)
                .putCoin(Money.TEN_CENTS);

        BigDecimal walletValue = wallet.calculator().getMoneyMapValue();
        BigDecimal valueToRemove = new BigDecimal("2.50");

        // when
        Map<Money, Integer> removedCoins = wallet.removeValueInCoins(valueToRemove);

        // then
        assertThat(wallet.calculator().getMoneyMapValue().compareTo(walletValue.subtract(valueToRemove)), is(0));
        assertThat(new MoneyDelegate(removedCoins).getMoneyMapValue().compareTo(valueToRemove), is(0));
    }

//    @Test
//    public void should_throw_exception_when_not_enough_coins_in_wallet() {
//        // given
//        Wallet coinWallet = new Wallet()
//                .putCoin(Coin.COIN_5)
//                .putCoin(Coin.COIN_1, 3)
//                .putCoin(Coin.COIN_0_2)
//                .putCoin(Coin.COIN_0_1, 2);
//        // expect
//        assertThatThrownBy(() -> coinWallet.removeValueInCoins(new BigDecimal("2.50")))
//                .isExactlyInstanceOf(IllegalStateException.class)
//                .hasMessageContaining("2.50");
//    }
}