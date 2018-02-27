package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class RemoveFoodFromCategory {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String foodToRemove;
    private final long expectedVersion;

    public RemoveFoodFromCategory(FoodCategoryIdentifier categoryIdentifier, String foodToRemove, long expectedVersion) {
        this.categoryIdentifier = categoryIdentifier;
        this.foodToRemove = foodToRemove;
        this.expectedVersion = expectedVersion;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getFoodToRemove() {
        return foodToRemove;
    }

    public long getExpectedVersion() {
        return expectedVersion;
    }
}
