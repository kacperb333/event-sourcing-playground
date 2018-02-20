package pl.kacperb333.java.foodbook.food.inventory.exceptions;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryStatus;

public class FoodCategoryInWrongStatusException extends Exception {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final FoodCategoryStatus wrongStatus;

    public FoodCategoryInWrongStatusException(FoodCategoryIdentifier categoryIdentifier, FoodCategoryStatus wrongStatus) {
        super(String.format("Category (%s) in wrong status: '%s'", categoryIdentifier, wrongStatus));
        this.categoryIdentifier = categoryIdentifier;
        this.wrongStatus = wrongStatus;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public FoodCategoryStatus getWrongStatus() {
        return wrongStatus;
    }
}
