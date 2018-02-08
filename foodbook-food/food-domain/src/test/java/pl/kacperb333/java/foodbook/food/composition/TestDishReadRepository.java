package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.test.TestReadRepository;

import java.util.concurrent.ConcurrentMap;

class TestDishReadRepository extends TestReadRepository<BasicDish, BasicDish.Identifier> implements DishReadRepository {


    protected TestDishReadRepository(ConcurrentMap<BasicDish.Identifier, BasicDish> database) {
        super(database);
    }

    @Override
    public boolean existsByName(String name) {
        return database.values().stream().anyMatch(d-> name.equals(d.getName()));
    }

}
