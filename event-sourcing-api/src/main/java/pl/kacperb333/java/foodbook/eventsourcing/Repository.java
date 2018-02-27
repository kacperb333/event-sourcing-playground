package pl.kacperb333.java.foodbook.eventsourcing;

public interface Repository<T extends AggregateRoot> {
    void save(T toSave);
    T load(T aggregateRoot);
}
