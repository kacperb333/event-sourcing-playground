package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.food.inventory.command.*;
import pl.kacperb333.java.foodbook.food.inventory.exceptions.*;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

public class FoodInventoryFacade {
    private final FoodCategoryRepository foodCategoryRepository;

    FoodInventoryFacade(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategoryIdentifier createCategory(CreateFoodCategoryCommand command)
            throws FoodCategoryWithNameAlreadyExistsException {
        FoodCategory newCategory = FoodCategory.newCategory(
                foodCategoryRepository,
                command.getCategoryName()
        );
        save(newCategory, 0L);
        return newCategory.getIdentifier();
    }

    public void renameCategory(RenameFoodCategoryCommand command) throws FoodCategoryWithNameAlreadyExistsException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier(), command.getExpectedVersion());
        category.rename(command.getNewCategoryName(), foodCategoryRepository);
        save(category, command.getExpectedVersion());
    }

    public void disableCategory(DisableFoodCategoryCommand command) throws FoodCategoryInWrongStatusException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier(), command.getExpectedVersion());
        category.disable();
        save(category, command.getExpectedVersion());
    }

    public void removeCategory(RenameFoodCategoryCommand command)
            throws FoodCategoryNotEmptyException, FoodCategoryInWrongStatusException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier(), command.getExpectedVersion());
        category.remove();
        save(category, command.getExpectedVersion());
    }

    public void addFoodToCategory(AddFoodToCategoryCommand command) throws FoodAlreadyPresentInCategoryException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier(), command.getExpectedVersion());
        category.addFood(command.getFoodToAdd());
        save(category, command.getExpectedVersion());
    }

    public void removeFoodFromCategory(RemoveFoodFromCategory command) throws FoodNotPresentInCategoryException {
        FoodCategory category = loadFromRepository(command.getCategoryIdentifier(), command.getExpectedVersion());
        category.removeFood(command.getFoodToRemove());
        save(category, command.getExpectedVersion());
    }

    private FoodCategory loadFromRepository(FoodCategoryIdentifier identifier, long expectedVersion) {
        FoodCategory category = FoodCategory.identifiedBy(identifier);
        foodCategoryRepository.load(category, expectedVersion);
        return category;
    }

    private void save(FoodCategory foodCategory, long expectedVersion) {
        foodCategoryRepository.save(foodCategory, expectedVersion);
    }
}
