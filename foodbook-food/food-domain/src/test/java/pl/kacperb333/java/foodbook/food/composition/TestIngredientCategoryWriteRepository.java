package pl.kacperb333.java.foodbook.food.composition;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

class TestIngredientCategoryWriteRepository implements IngredientCategoryWriteRepository {

    private final ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database;
    private final AtomicLong sequence = new AtomicLong(0L);

    public TestIngredientCategoryWriteRepository(ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database) {
        this.database = database;
    }

    @Override
    public IngredientCategory save(IngredientCategory toSave) {
        return toSave.getId() != null ? update(toSave) : create(toSave);
    }

    private IngredientCategory create(IngredientCategory toSave) {
        IngredientCategory toCreate = IngredientCategory.from(
                new IngredientCategory.Identifier(sequence.getAndIncrement()),
                0L,
                toSave.getName()
        );
        database.put(toCreate.getId(), toCreate);
        return toCreate;
    }

    private IngredientCategory update(IngredientCategory toSave) {
        IngredientCategory toUpdate = IngredientCategory.from(
                toSave.getId(),
                toSave.getVersion() + 1,
                toSave.getName()
        );
        database.put(toUpdate.getId(), toUpdate);
        return toUpdate;
    }
}
