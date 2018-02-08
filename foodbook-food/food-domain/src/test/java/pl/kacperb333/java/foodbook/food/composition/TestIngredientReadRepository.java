package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.concurrent.ConcurrentMap;

class TestIngredientReadRepository extends TestReadRepository<BasicIngredient, BasicIngredient.Identifier>
        implements IngredientReadRepository {


    protected TestIngredientReadRepository(ConcurrentMap<BasicIngredient.Identifier, BasicIngredient> database) {
        super(database);
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(i -> name.equals(i.getName()));
    }

}
