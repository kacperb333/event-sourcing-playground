package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class RenameFoodCategoryCommand {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String newCategoryName;
    private final long expectedVersion;

    public RenameFoodCategoryCommand(FoodCategoryIdentifier categoryIdentifier, String newCategoryName, long expectedVersion) {
        this.categoryIdentifier = categoryIdentifier;
        this.newCategoryName = newCategoryName;
        this.expectedVersion = expectedVersion;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }

    public long getExpectedVersion() {
        return expectedVersion;
    }
}
