package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

class FoodCategoryRemovedEvent extends Event {
    public FoodCategoryRemovedEvent(FoodCategoryIdentifier aggregateIdentifier) {
        super(aggregateIdentifier);
    }
}
