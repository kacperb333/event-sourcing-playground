package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestWriteRepository;

import java.util.concurrent.ConcurrentMap;

class TestIngredientCategoryWriteRepository extends TestWriteRepository<IngredientCategory, IngredientCategory.Identifier>
        implements IngredientCategoryWriteRepository {


    public TestIngredientCategoryWriteRepository(ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database) {
        super(database);
    }
}
