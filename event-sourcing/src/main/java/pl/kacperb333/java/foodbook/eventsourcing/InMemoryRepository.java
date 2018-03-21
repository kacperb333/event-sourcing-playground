package pl.kacperb333.java.foodbook.eventsourcing;

import org.apache.commons.lang3.exception.ExceptionUtils;

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
    public AggregateType load(IdentifierType aggregateIdentifier) throws AggregateNotFoundException {
        var aggregate = instantiateAggregate(aggregateIdentifier);

        var existingAggregateEvents = underlyingEventStore.getCommittedEvents(aggregateIdentifier);
        if (existingAggregateEvents.isEmpty()) {
            throw new AggregateNotFoundException(reifiedAggregateType, aggregateIdentifier);
        }
        existingAggregateEvents.forEach(aggregate::applyExistingEvent);

        return aggregate;
    }

    private AggregateType instantiateAggregate(IdentifierType aggregateIdentifier) {
        try {
            var aggregateConstructor = MethodHandles.lookup().findConstructor(reifiedAggregateType,
                    MethodType.methodType(void.class, aggregateIdentifier.getClass()));
            return reifiedAggregateType.cast(aggregateConstructor.invokeWithArguments(aggregateIdentifier));
        } catch (Throwable ex) {
            return ExceptionUtils.rethrow(ex);
        }
    }

    @Override
    public AggregateType loadExact(IdentifierType aggregateIdentifier, long expectedVersion)
            throws AggregateNotFoundException, NoExactResultException{

        var loadedAggregate = load(aggregateIdentifier);
        if (loadedAggregate.getVersion() != expectedVersion) {
            throw new NoExactResultException(expectedVersion, loadedAggregate.getVersion());
        }

        return loadedAggregate;
    }

    @Override
    public AggregateType loadAtLeast(IdentifierType aggregateIdentifier, long leastExpectedVersion)
            throws AggregateNotFoundException, NoExpectedResultException {

        var loadedAggregate = load(aggregateIdentifier);
        if (loadedAggregate.getVersion() < leastExpectedVersion) {
            throw new NoExpectedResultException(leastExpectedVersion, loadedAggregate.getVersion());
        }

        return loadedAggregate;
    }
}
