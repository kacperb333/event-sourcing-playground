package pl.kacperb333.java.foodbook.food.composition;

import java.util.Optional;

interface IngredientCategoryReadRepository {
    Optional<IngredientCategory> find(IngredientCategory.Identifier id);

    boolean existsByName(String name);
}
