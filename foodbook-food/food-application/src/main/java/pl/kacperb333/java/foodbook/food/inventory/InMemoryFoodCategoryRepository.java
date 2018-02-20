package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.EventStore;
import pl.kacperb333.java.foodbook.eventsourcing.EventStoreRepository;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryFoodCategoryRepository extends EventStoreRepository<FoodCategory> implements FoodCategoryRepository {

    private final AtomicLong sequence = new AtomicLong(1);
    private final LocalActiveCategoryNamesReadModel activeCategoryNamesReadModel;

    InMemoryFoodCategoryRepository(EventStore underlyingEventStore,
                                   LocalActiveCategoryNamesReadModel activeCategoryNamesReadModel) {
        super(underlyingEventStore);
        this.activeCategoryNamesReadModel = activeCategoryNamesReadModel;
    }

    @Override
    public Collection<String> getAllActiveFoodCategoryNames() {
        return activeCategoryNamesReadModel.getExistingCategoryNames().values();
    }

    @Override
    public FoodCategoryIdentifier provideNewIdentifier() {
        return new FoodCategoryIdentifier(sequence.getAndIncrement());
    }
}
