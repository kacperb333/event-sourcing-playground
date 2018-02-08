package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

class TestIngredientReadRepository extends TestReadRepository<BasicIngredient>
        implements IngredientReadRepository {

    TestIngredientReadRepository() {
        super();
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
