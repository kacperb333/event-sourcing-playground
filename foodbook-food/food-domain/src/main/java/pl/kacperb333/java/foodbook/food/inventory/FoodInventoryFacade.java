package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.food.inventory.command.*;
import pl.kacperb333.java.foodbook.food.inventory.exceptions.*;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class FoodInventoryFacade {
    private final FoodCategoryRepository foodCategoryRepository;

    FoodInventoryFacade(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategoryIdentifier createCategory(CreateFoodCategoryCommand command) throws FoodCategoryWithNameAlreadyExistsException {
        FoodCategory newCategory = FoodCategory.newCategory(
                foodCategoryRepository,
                command.getCategoryName()
        );
        save(newCategory);
        return newCategory.getIdentifier();
    }

    public void renameCategory(RenameFoodCategoryCommand command) throws FoodCategoryWithNameAlreadyExistsException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier());
        category.rename(command.getNewCategoryName(), foodCategoryRepository);
        save(category);
    }

    public void disableCategory(DisableFoodCategoryCommand command) throws FoodCategoryInWrongStatusException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier());
        category.disable();
        save(category);
    }

    public void removeCategory(RenameFoodCategoryCommand command) throws FoodCategoryNotEmptyException, FoodCategoryInWrongStatusException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier());
        category.remove();
        save(category);
    }

    public void addFoodToCategory(AddFoodToCategoryCommand command) throws FoodAlreadyPresentInCategoryException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier());
        category.addFood(command.getFoodToAdd());
        save(category);
    }

    public void removeFoodFromCategory(RemoveFoodFromCategory command) throws FoodNotPresentInCategoryException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier());
        category.removeFood(command.getFoodToRemove());
        save(category);
    }

    private FoodCategory loadFromRepository(FoodCategoryIdentifier identifier) {
        FoodCategory category = FoodCategory.identifiedBy(identifier);
        foodCategoryRepository.load(category);
        return category;
    }

    private void save(FoodCategory foodCategory) {
        foodCategoryRepository.save(foodCategory);
    }
}
