package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.AggregateRoot;
import pl.kacperb333.java.foodbook.food.inventory.exceptions.*;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notNull;

class FoodCategory extends AggregateRoot<FoodCategoryIdentifier> {

    private String categoryName;
    private FoodCategoryStatus status;
    private final Set<Food> foodInCategory;

    private FoodCategory(FoodCategoryIdentifier identifier) {
        super(identifier);
        this.foodInCategory = new HashSet<>();
    }

    static FoodCategory newCategory(FoodCategoryRepository foodCategoryRepository, String categoryName) throws FoodCategoryWithNameAlreadyExistsException {
        if (foodCategoryRepository.getAllActiveFoodCategoryNames().contains(categoryName)) {
            throw new FoodCategoryWithNameAlreadyExistsException(categoryName);
        }
        FoodCategory createdCategory = identifiedBy(foodCategoryRepository.provideNewIdentifier());
        createdCategory.applyEvent(new FoodCategoryCreatedEvent(createdCategory.getIdentifier(), categoryName));
        return createdCategory;
    }

    static FoodCategory identifiedBy(FoodCategoryIdentifier identifier) {
        notNull(identifier);
        return new FoodCategory(identifier);
    }

    private void apply(FoodCategoryCreatedEvent foodCategoryCreatedEvent) {
        categoryName = foodCategoryCreatedEvent.getCategoryName();
        status = FoodCategoryStatus.ACTIVE;
    }

    void rename(String newCategoryName, FoodCategoryRepository foodCategoryRepository) throws FoodCategoryWithNameAlreadyExistsException {
        if (foodCategoryRepository.getAllActiveFoodCategoryNames().contains(newCategoryName)) {
            throw new FoodCategoryWithNameAlreadyExistsException(newCategoryName);
        }
        applyEvent(new FoodCategoryRenamedEvent(getIdentifier(), newCategoryName));
    }

    private void apply(FoodCategoryRenamedEvent foodCategoryRenamedEvent) {
        categoryName = foodCategoryRenamedEvent.getNewCategoryName();
    }

    void disable() throws FoodCategoryInWrongStatusException {
        if (status != FoodCategoryStatus.ACTIVE) {
            throw new FoodCategoryInWrongStatusException(getIdentifier(), status);
        }
        applyEvent(new FoodCategoryDisabledEvent(getIdentifier()));
    }

    private void apply(FoodCategoryDisabledEvent foodCategoryDisabledEvent) {
        status = FoodCategoryStatus.DISABLED;
    }

    void remove() throws FoodCategoryInWrongStatusException, FoodCategoryNotEmptyException {
        if (status != FoodCategoryStatus.DISABLED) {
            throw new FoodCategoryInWrongStatusException(getIdentifier(), status);
        }
        if (!foodInCategory.isEmpty()) {
            throw new FoodCategoryNotEmptyException(getIdentifier(),
                    foodInCategory.stream().map(Food::getName).collect(Collectors.toSet()));
        }
        applyEvent(new FoodCategoryRemovedEvent(getIdentifier()));
    }

    private void apply(FoodCategoryRemovedEvent foodCategoryRemovedEvent) {
        status = FoodCategoryStatus.REMOVED;
    }

    void addFood(String foodToAdd) throws FoodAlreadyPresentInCategoryException {
        if (foodInCategory.stream().anyMatch(f -> foodToAdd.equals(f.getName()))) {
            throw new FoodAlreadyPresentInCategoryException(getIdentifier(), foodToAdd);
        }
        applyEvent(new FoodAddedToCategoryEvent(getIdentifier(), foodToAdd));
    }

    private void apply(FoodAddedToCategoryEvent foodAddedToCategoryEvent) {
        foodInCategory.add(new Food(foodAddedToCategoryEvent.getFoodName()));
    }

    void removeFood(String foodToRemove) throws FoodNotPresentInCategoryException {
        if (foodInCategory.stream().noneMatch(f -> foodToRemove.equals(f.getName()))) {
            throw new FoodNotPresentInCategoryException(getIdentifier(), foodToRemove);
        }
        applyEvent(new FoodRemovedFromCategoryEvent(getIdentifier(), foodToRemove));
    }

    private void apply(FoodRemovedFromCategoryEvent foodRemovedFromCategoryEvent) {
        foodInCategory.remove(new Food(foodRemovedFromCategoryEvent.getFoodToRemove()));
    }
}
