package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class DisableFoodCategoryCommand {
    private final FoodCategoryIdentifier categoryIdentifier;

    public DisableFoodCategoryCommand(FoodCategoryIdentifier categoryIdentifier) {
        this.categoryIdentifier = categoryIdentifier;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }
}
