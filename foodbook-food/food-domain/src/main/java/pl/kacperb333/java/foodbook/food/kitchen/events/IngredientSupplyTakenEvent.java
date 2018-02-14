package pl.kacperb333.java.foodbook.food.kitchen.events;

import lombok.Value;

@Value
public class IngredientSupplyTakenEvent {
    private final String ingredientName;
    private final int howMany;
}
