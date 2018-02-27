package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

class FoodCategoryCreatedEvent extends Event {

    private final String categoryName;

    public FoodCategoryCreatedEvent(FoodCategoryIdentifier aggregateIdentifier, String categoryName) {
        super(aggregateIdentifier);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
