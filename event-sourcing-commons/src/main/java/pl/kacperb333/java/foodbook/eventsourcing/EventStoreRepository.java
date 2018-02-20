package pl.kacperb333.java.foodbook.eventsourcing;

public abstract class EventStoreRepository<T extends AggregateRoot> implements Repository<T> {
    private final EventStore underlyingEventStore;

    protected EventStoreRepository(EventStore underlyingEventStore) {
        this.underlyingEventStore = underlyingEventStore;
    }

    @Override
    public void save(T toSave) {
        toSave.commitEvents(underlyingEventStore);
    }

    @Override
    public T load(T aggregateRoot) {
        aggregateRoot.applyHistory(aggregateRoot.getIdentifier(), underlyingEventStore);
        return aggregateRoot;
    }
}
