package pl.kacperb333.java.foodbook.food.composition.ingredients.dto;

public class IngredientCategoryAlreadyExistsException extends RuntimeException {

    private final String categoryName;

    public IngredientCategoryAlreadyExistsException(String categoryName) {
        super(String.format("Category with name: %s already exists", categoryName));
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
