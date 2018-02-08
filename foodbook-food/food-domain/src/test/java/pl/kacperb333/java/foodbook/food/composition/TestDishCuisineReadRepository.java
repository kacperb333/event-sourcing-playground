package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.concurrent.ConcurrentMap;

class TestDishCuisineReadRepository extends TestReadRepository<DishCuisine, DishCuisine.Identifier>
        implements DishCuisineReadRepository {


    protected TestDishCuisineReadRepository(ConcurrentMap<DishCuisine.Identifier, DishCuisine> database) {
        super(database);
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(c-> name.equals(c.getName()));
    }


}
