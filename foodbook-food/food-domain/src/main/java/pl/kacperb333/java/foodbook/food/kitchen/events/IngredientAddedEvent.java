package pl.kacperb333.java.foodbook.food.kitchen.events;

import lombok.Value;
import pl.kacperb333.java.foodbook.food.kitchen.values.EnergyValue;

@Value
public class IngredientAddedEvent {
    private final String ingredientName;
    private final EnergyValue ingredientEnergyValue;
    private final String shelfLabel;
    private final int initialSupply;

}
