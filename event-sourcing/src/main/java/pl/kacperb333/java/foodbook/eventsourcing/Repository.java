package pl.kacperb333.java.foodbook.eventsourcing;

public interface Repository<T extends AggregateRoot> {
    void save(T toSave, long expectedVersion);
    T load(T aggregateRoot, long expectedVersion);
}
