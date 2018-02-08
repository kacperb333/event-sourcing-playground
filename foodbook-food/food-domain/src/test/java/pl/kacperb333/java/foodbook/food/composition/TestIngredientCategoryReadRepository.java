package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.concurrent.ConcurrentMap;

class TestIngredientCategoryReadRepository extends TestReadRepository<IngredientCategory, IngredientCategory.Identifier>
        implements IngredientCategoryReadRepository {


    protected TestIngredientCategoryReadRepository(ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database) {
        super(database);
    }

}
