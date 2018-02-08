package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

class TestIngredientCategoryReadRepository extends TestReadRepository<IngredientCategory, IngredientCategory.Identifier>
        implements IngredientCategoryReadRepository {

    TestIngredientCategoryReadRepository() {
        super();
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(c -> name.equals(c.getName()));
    }

    @Override
    public IngredientCategory.Identifier provideNewIdentifier() {
        return new IngredientCategory.Identifier(sequence.getAndIncrement());
    }
}
