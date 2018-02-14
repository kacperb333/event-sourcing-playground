package pl.kacperb333.java.foodbook.food.kitchen.events;

import lombok.Value;

@Value
public class IngredientSupplyAddedEvent {
    private final String ingredientName;
    private final int howMany;
}
