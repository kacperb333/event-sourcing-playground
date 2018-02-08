package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestWriteRepository;

class TestIngredientCategoryWriteRepository extends TestWriteRepository<IngredientCategory>
        implements IngredientCategoryWriteRepository {


    protected TestIngredientCategoryWriteRepository(TestIngredientCategoryReadRepository underlyingReadRepository) {
        super(underlyingReadRepository);
    }
}
