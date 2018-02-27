package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class AddFoodToCategoryCommand {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String foodToAdd;
    private final long expectedVersion;

    public AddFoodToCategoryCommand(FoodCategoryIdentifier categoryIdentifier, String foodToAdd, long expectedVersion) {
        this.categoryIdentifier = categoryIdentifier;
        this.foodToAdd = foodToAdd;
        this.expectedVersion = expectedVersion;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getFoodToAdd() {
        return foodToAdd;
    }

    public long getExpectedVersion() {
        return expectedVersion;
    }
}
