package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.ConcurrentModificationException;

public abstract class EventStoreRepository<T extends AggregateRoot> implements Repository<T> {
    private final EventStore underlyingEventStore;

    protected EventStoreRepository(EventStore underlyingEventStore) {
        this.underlyingEventStore = underlyingEventStore;
    }

    @Override
    public void save(T toSave, long expectedVersion) {
        toSave.commitEvents(underlyingEventStore, expectedVersion);
    }

    @Override
    public T load(T aggregateRoot, long expectedVersion) {
        aggregateRoot.applyHistory(underlyingEventStore);
        if (aggregateRoot.getVersion() != expectedVersion) {
            throw new ConcurrentModificationException();
        }
        return aggregateRoot;
    }
}
