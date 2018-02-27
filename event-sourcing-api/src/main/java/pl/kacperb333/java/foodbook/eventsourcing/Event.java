package pl.kacperb333.java.foodbook.eventsourcing;

public abstract class Event<T extends Identifier> {
    private final T aggregateIdentifier;

    protected Event(T aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public T getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}
