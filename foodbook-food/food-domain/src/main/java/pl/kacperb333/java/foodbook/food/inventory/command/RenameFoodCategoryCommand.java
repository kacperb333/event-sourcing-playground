package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class RenameFoodCategoryCommand {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String newCategoryName;

    public RenameFoodCategoryCommand(FoodCategoryIdentifier categoryIdentifier, String newCategoryName) {
        this.categoryIdentifier = categoryIdentifier;
        this.newCategoryName = newCategoryName;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }
}
