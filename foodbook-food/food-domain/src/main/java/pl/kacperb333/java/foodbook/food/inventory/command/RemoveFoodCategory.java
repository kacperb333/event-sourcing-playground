package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class RemoveFoodCategory {
    private final FoodCategoryIdentifier categoryIdentifier;

    public RemoveFoodCategory(FoodCategoryIdentifier categoryIdentifier) {
        this.categoryIdentifier = categoryIdentifier;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }
}
