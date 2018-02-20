package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

class FoodCategoryRenamedEvent extends Event<FoodCategoryIdentifier> {
    private final String newCategoryName;

    public FoodCategoryRenamedEvent(FoodCategoryIdentifier aggregateIdentifier, String newCategoryName) {
        super(aggregateIdentifier);
        this.newCategoryName = newCategoryName;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }
}
