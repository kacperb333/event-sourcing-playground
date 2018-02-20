package pl.kacperb333.java.foodbook.food.inventory.exceptions;

import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

import java.util.Set;

public class FoodCategoryNotEmptyException extends Exception {
    private final FoodCategoryIdentifier categoryIdentifier;
    private final Set<String> food;

    public FoodCategoryNotEmptyException(FoodCategoryIdentifier categoryIdentifier, Set<String> food) {
        super(String.format("Category (%s) is not empty. Contains: %s", categoryIdentifier, food));
        this.categoryIdentifier = categoryIdentifier;
        this.food = food;
    }

    public FoodCategoryIdentifier getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public Set<String> getFood() {
        return food;
    }
}
