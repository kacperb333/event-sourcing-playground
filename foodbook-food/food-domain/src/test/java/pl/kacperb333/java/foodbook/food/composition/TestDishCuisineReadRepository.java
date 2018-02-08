package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

class TestDishCuisineReadRepository extends TestReadRepository<DishCuisine>
        implements DishCuisineReadRepository {

    TestDishCuisineReadRepository() {
        super();
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(c-> name.equals(c.getName()));
    }

    @Override
    public DishCuisine.Identifier provideNewIdentifier() {
        return new DishCuisine.Identifier(sequence.getAndIncrement());
    }
}
