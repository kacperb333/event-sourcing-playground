package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

class FoodAddedToCategoryEvent extends Event {

    private final String foodName;

    public FoodAddedToCategoryEvent(FoodCategoryIdentifier aggregateIdentifier, String foodName) {
        super(aggregateIdentifier);
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }
}
