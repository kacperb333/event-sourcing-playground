package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.Map;

class TestDishCuisineReadRepository extends TestReadRepository<DishCuisine, DishCuisine.Identifier>
        implements DishCuisineReadRepository {

    TestDishCuisineReadRepository() {
        super();
    }

    TestDishCuisineReadRepository(Map<DishCuisine.Identifier, DishCuisine> initialDatabase) {
        super(initialDatabase);
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
