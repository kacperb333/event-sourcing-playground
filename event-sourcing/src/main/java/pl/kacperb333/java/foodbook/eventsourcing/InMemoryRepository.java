package pl.kacperb333.java.foodbook.eventsourcing;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
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
            var aggregateConstructor = MethodHandles.lookup()
                    .findConstructor(reifiedAggregateType, MethodType.methodType(void.class, aggregateIdentifier.getClass()));

            var aggregate = reifiedAggregateType.cast(
                    aggregateConstructor.invokeWithArguments(aggregateIdentifier));
            aggregate.applyHistory(underlyingEventStore);
            return aggregate;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AggregateType loadExact(IdentifierType aggregateIdentifier, long expectedVersion)
            throws NoExactResultException{

        var loadedAggregate = load(aggregateIdentifier);
        if (loadedAggregate.getVersion() != expectedVersion) {
            throw new NoExactResultException(expectedVersion, loadedAggregate.getVersion());
        }

        return loadedAggregate;
    }

    @Override
    public AggregateType loadAtLeast(IdentifierType aggregateIdentifier, long leastExpectedVersion)
            throws NoExpectedResultException {

        var loadedAggregate = load(aggregateIdentifier);
        if (loadedAggregate.getVersion() < leastExpectedVersion) {
            throw new NoExpectedResultException(leastExpectedVersion, loadedAggregate.getVersion());
        }

        return loadedAggregate;
    }
}
