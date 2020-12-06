package Kata.gathering.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Money {

    TWENTY_EUROS(new BigDecimal("20")),
    TEN_EUROS(new BigDecimal("10")),
    FIVE_EUROS(new BigDecimal("5")),
    TWO_EUROS(new BigDecimal("2")),
    ONE_EURO(new BigDecimal("1")),
    FIFTY_CENTS(new BigDecimal("0.5")),
    TWENTY_CENTS(new BigDecimal("0.2")),
    TEN_CENTS(new BigDecimal("0.1")),
    FIVE_CENTS(new BigDecimal("0.05")),
    ONE_CENT(new BigDecimal("0.01"));

    private final BigDecimal moneyValue;

    public static Optional<Money> fromDenomination(String value) {

        if(!NumberUtils.isNumber(value))
            return Optional.empty();

        return Stream.of(values())
                .filter(c -> c.moneyValue.compareTo(new BigDecimal(value)) == 0 )
                .findFirst();
    }
}