package pl.kacperb333.java.foodbook.eventsourcing;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InMemoryRepository<AggregateType extends AggregateRoot<IdentifierType>, IdentifierType>
        implements Repository<AggregateType, IdentifierType> {

    private final EventStore<IdentifierType> underlyingEventStore;
    private final Class<AggregateType> reifiedAggregateType;

    public InMemoryRepository(Class<AggregateType> reifiedAggregateType,
                              EventStore<IdentifierType> underlyingEventStore) {
        this.underlyingEventStore = underlyingEventStore;
        this.reifiedAggregateType = reifiedAggregateType;
    }

    @Override
    public void save(AggregateType toSave, long expectedVersion) {
        toSave.commitEvents(underlyingEventStore, expectedVersion);
    }

    @Override
    public AggregateType load(IdentifierType aggregateIdentifier) {
        try {
            Constructor<AggregateType> aggregateConstructor =
                    reifiedAggregateType.getDeclaredConstructor(aggregateIdentifier.getClass());
            aggregateConstructor.setAccessible(true);

            AggregateType aggregate = aggregateConstructor.newInstance(aggregateIdentifier);
            aggregate.applyHistory(underlyingEventStore);
            return aggregate;
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public AggregateType loadExact(IdentifierType aggregateIdentifier, long expectedVersion)
            throws NoExactResultException{

        AggregateType loadedAggregate = load(aggregateIdentifier);
        if (loadedAggregate.getVersion() != expectedVersion) {
            throw new NoExactResultException(expectedVersion, loadedAggregate.getVersion());
        }

        return loadedAggregate;
    }
}
