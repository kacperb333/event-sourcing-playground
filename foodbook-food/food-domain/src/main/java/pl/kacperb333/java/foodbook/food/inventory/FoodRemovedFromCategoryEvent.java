package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

class FoodRemovedFromCategoryEvent extends Event {
    private final String foodToRemove;

    public FoodRemovedFromCategoryEvent(FoodCategoryIdentifier aggregateIdentifier, String foodToRemove) {
        super(aggregateIdentifier);
        this.foodToRemove = foodToRemove;
    }

    public String getFoodToRemove() {
        return foodToRemove;
    }
}
