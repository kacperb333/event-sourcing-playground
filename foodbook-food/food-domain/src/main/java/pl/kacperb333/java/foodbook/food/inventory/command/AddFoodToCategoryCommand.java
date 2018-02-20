package pl.kacperb333.java.foodbook.food.inventory.command;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class AddFoodToCategoryCommand {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String foodToAdd;

    public AddFoodToCategoryCommand(FoodCategoryIdentifier categoryIdentifier, String foodToAdd) {
        this.categoryIdentifier = categoryIdentifier;
        this.foodToAdd = foodToAdd;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getFoodToAdd() {
        return foodToAdd;
    }
}
