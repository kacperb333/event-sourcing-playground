package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.Map;

class TestDishReadRepository extends TestReadRepository<BasicDish, BasicDish.Identifier> implements DishReadRepository {

    TestDishReadRepository() {
        super();
    }

    TestDishReadRepository(Map<BasicDish.Identifier, BasicDish> initialDatabase) {
        super(initialDatabase);
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(d-> name.equals(d.getName()));
    }

    @Override
    public BasicDish.Identifier provideNewIdentifier() {
        return new BasicDish.Identifier(sequence.getAndIncrement());
    }
}
