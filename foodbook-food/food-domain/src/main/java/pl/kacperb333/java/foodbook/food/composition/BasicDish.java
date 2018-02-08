package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;
import pl.kacperb333.java.foodbook.domain.commonvalue.Money;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.apache.commons.lang3.Validate.*;

class BasicDish implements DomainEntity<BasicDish.Identifier> {
    @Value
    static class Identifier implements UniqueIdentifier<Long> {
        final Long id;
    }

    private final Identifier id;
    private final String name;
    private final Set<BasicIngredient> ingredients;
    private final Set<DishCuisine> cuisines;
    private final Money additionalPrice;

    private BasicDish(Identifier id, String name, Set<BasicIngredient> ingredients, Set<DishCuisine> cuisines,
                      Money additionalPrice) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.cuisines = cuisines;
        this.additionalPrice = additionalPrice;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<BasicIngredient> getIngredientsView() {
        return Collections.unmodifiableSet(ingredients);
    }

    public Set<DishCuisine> getCuisinesView() {
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
