package pl.kacperb333.java.foodbook.food.kitchen.events;

import lombok.Value;

@Value
public class IngredientRemovedEvent {
    private final String ingredientName;
}
