package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import pl.kacperb333.java.foodbook.domain.commonvalue.Money;

import java.util.Collections;
import java.util.Set;

@Value
class BasicDish {
    @Value
    static class Identifier {
        final Long id;
    }

    private final Identifier id;
    private final long version;
    private final String name;
    private final Set<BasicIngredient> ingredients;
    private final Set<DishCuisine> cuisines;
    private final Money additionalPrice;

    public Set<BasicIngredient> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public Set<DishCuisine> getCuisines() {
        return Collections.unmodifiableSet(cuisines);
    }

    public EnergyValue energyValue() {
        return ingredients.stream()
                .map(BasicIngredient::getEnergyValue)
                .reduce(EnergyValue.ofKcal(0), EnergyValue::plus);
    }

    public Money getAdditionalPrice() {
        return additionalPrice;
    }

    public Money getTotalPrice() {
        Money ingredientPrice = ingredients.stream()
                .map(BasicIngredient::getPrice)
                .reduce(Money.ZERO, Money::plus);

        return ingredientPrice.plus(additionalPrice);
    }
}
