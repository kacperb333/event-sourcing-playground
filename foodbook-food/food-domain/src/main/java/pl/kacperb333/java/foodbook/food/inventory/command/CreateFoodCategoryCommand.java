package pl.kacperb333.java.foodbook.food.inventory.command;

public class CreateFoodCategoryCommand {
    private final String categoryName;

    public CreateFoodCategoryCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
