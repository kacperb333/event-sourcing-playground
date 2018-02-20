package pl.kacperb333.java.foodbook.food.inventory.exceptions;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class FoodAlreadyPresentInCategoryException extends Exception {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final String food;

    public FoodAlreadyPresentInCategoryException(FoodCategoryIdentifier categoryIdentifier, String food) {
        super(String.format("Food named '%s' is already present in category (%s)", food, categoryIdentifier));
        this.categoryIdentifier = categoryIdentifier;
        this.food = food;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public String getFood() {
        return food;
    }
}
