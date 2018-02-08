package pl.kacperb333.java.foodbook.food.composition.ingredients;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.kacperb333.java.foodbook.domain.commonvalue.Money;
import pl.kacperb333.java.foodbook.food.composition.ingredients.values.BasicIngredientId;
import pl.kacperb333.java.foodbook.food.composition.values.EnergyValue;


@RequiredArgsConstructor
@ToString
@Getter
class BasicIngredient {
    private final BasicIngredientId id;
    private final long version;
    private final String name;
    private final EnergyValue energyValue;
    private final Money price;
    private final IngredientCategory category;
}
