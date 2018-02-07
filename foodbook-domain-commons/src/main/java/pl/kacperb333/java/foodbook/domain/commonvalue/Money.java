package pl.kacperb333.java.foodbook.domain.commonvalue;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {
    public static Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal value;

    public Money plus(Money toAdd) {
        return new Money(value.add(toAdd.value));
    }
}
