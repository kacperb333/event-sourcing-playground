package pl.kacperb333.java.foodbook.food.composition.ingredients;

import pl.kacperb333.java.foodbook.food.composition.ingredients.values.IngredientCategoryId;

import java.util.Optional;

interface IngredientCategoryReadRepository {
    Optional<IngredientCategory> find(IngredientCategoryId id);

    boolean existsByName(String name);
}
