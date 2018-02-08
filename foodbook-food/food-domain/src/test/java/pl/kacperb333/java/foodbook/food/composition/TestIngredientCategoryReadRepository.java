package pl.kacperb333.java.foodbook.food.composition;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

class TestIngredientCategoryReadRepository implements IngredientCategoryReadRepository {

    private final ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database;

    TestIngredientCategoryReadRepository(ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database) {
        this.database = database;
    }

    @Override
    public Optional<IngredientCategory> find(IngredientCategory.Identifier id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(v -> name.equals(v.getName()));
    }
}
