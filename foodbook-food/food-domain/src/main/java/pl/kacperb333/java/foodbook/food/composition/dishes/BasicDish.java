package pl.kacperb333.java.foodbook.food.composition.dishes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.kacperb333.java.foodbook.domain.commonvalue.Money;
import pl.kacperb333.java.foodbook.food.composition.dishes.values.BasicDishId;
import pl.kacperb333.java.foodbook.food.composition.ingredients.values.BasicIngredientId;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@ToString
@Getter
class BasicDish {

    private final BasicDishId id;
    private final long version;
    private final String name;
    private final Set<BasicIngredientId> ingredients;
    private final Set<DishCuisine> cuisines;
    private final Money additionalPrice;

    public Set<BasicIngredientId> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public Set<DishCuisine> getCuisines() {
        return Collections.unmodifiableSet(cuisines);
    }

}


