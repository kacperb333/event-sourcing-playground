package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class RemoveFoodFromCategory {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String foodToRemove;

    public RemoveFoodFromCategory(FoodCategoryIdentifier categoryIdentifier, String foodToRemove) {
        this.categoryIdentifier = categoryIdentifier;
        this.foodToRemove = foodToRemove;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getFoodToRemove() {
        return foodToRemove;
    }
}
