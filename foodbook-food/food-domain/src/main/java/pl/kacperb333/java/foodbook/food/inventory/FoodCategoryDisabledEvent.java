package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

class FoodCategoryDisabledEvent extends Event {
    public FoodCategoryDisabledEvent(FoodCategoryIdentifier aggregateIdentifier) {
        super(aggregateIdentifier);
    }
}
