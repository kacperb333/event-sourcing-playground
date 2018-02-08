package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import pl.kacperb333.java.foodbook.domain.commonvalue.Money;


@Value
class BasicIngredient {
    @Value
    static class Identifier {
        private final Long id;
    }

    private final Identifier id;
    private final long version;
    private final String name;
    private final EnergyValue energyValue;
    private final Money price;
    private final IngredientCategory category;
}
