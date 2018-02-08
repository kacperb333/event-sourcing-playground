package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

class TestDishReadRepository extends TestReadRepository<BasicDish, BasicDish.Identifier> implements DishReadRepository {

    TestDishReadRepository() {
        super();
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
