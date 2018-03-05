package pl.kacperb333.java.foodbook.eventsourcing;

public class InMemoryRepository<AggregateType extends AggregateRoot<IdentifierType>, IdentifierType>
        implements Repository<AggregateType, IdentifierType> {

    private final EventStore<IdentifierType> underlyingEventStore;

    public InMemoryRepository(EventStore<IdentifierType> underlyingEventStore) {
        this.underlyingEventStore = underlyingEventStore;
    }

    @Override
    public void save(AggregateType toSave, long expectedVersion) {

    }

    @Override
    public AggregateType load(IdentifierType aggregateIdentifier) {
        return null;
    }

    @Override
    public AggregateType loadExact(IdentifierType aggregateIdentifier, long expectedVersion) {
        return null;
    }
}
