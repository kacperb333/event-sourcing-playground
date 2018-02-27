package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class DisableFoodCategoryCommand {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final long expectedVersion;

    public DisableFoodCategoryCommand(FoodCategoryIdentifier categoryIdentifier, long expectedVersion) {
        this.categoryIdentifier = categoryIdentifier;
        this.expectedVersion = expectedVersion;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public long getExpectedVersion() {
        return expectedVersion;
    }
}
