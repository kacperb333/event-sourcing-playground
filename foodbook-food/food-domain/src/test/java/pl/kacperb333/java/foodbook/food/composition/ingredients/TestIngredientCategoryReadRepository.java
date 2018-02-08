package pl.kacperb333.java.foodbook.food.composition.ingredients;

import pl.kacperb333.java.foodbook.food.composition.ingredients.values.IngredientCategoryId;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

class TestIngredientCategoryReadRepository implements IngredientCategoryReadRepository {

    private final ConcurrentMap<IngredientCategoryId, IngredientCategory> database;

    TestIngredientCategoryReadRepository(ConcurrentMap<IngredientCategoryId, IngredientCategory> database) {
        this.database = database;
    }

    @Override
    public Optional<IngredientCategory> find(IngredientCategoryId id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(v -> name.equals(v.getName()));
    }
}
