package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.Map;

class TestIngredientReadRepository extends TestReadRepository<BasicIngredient, BasicIngredient.Identifier>
        implements IngredientReadRepository {

    TestIngredientReadRepository() {
        super();
    }

    TestIngredientReadRepository(Map<BasicIngredient.Identifier, BasicIngredient> initialDatabase) {
        super(initialDatabase);
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(i -> name.equals(i.getName()));
    }

    @Override
    public BasicIngredient.Identifier provideNewIdentifier() {
        return new BasicIngredient.Identifier(sequence.getAndIncrement());
    }
}
