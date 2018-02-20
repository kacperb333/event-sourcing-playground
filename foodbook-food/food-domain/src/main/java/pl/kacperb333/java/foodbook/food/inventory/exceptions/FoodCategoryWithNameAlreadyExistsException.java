package pl.kacperb333.java.foodbook.food.inventory.exceptions;

public class FoodCategoryWithNameAlreadyExistsException extends Exception {
    private final String existingName;

    public FoodCategoryWithNameAlreadyExistsException(String existingName) {
        super(String.format("Category with name '%s' already exists: (%s)", existingName));
        this.existingName = existingName;
    }

    public String getExistingName() {
        return existingName;
    }
}
