package pl.kacperb333.java.foodbook.eventsourcing;

public abstract class Event<IdentifierType> {
    private final IdentifierType aggregateIdentifier;
    private final long version;

    protected Event(IdentifierType aggregateIdentifier, long version) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.version = version;
    }

    public Event(IdentifierType aggregateIdentifier) {
        this(aggregateIdentifier, 0L);
    }

    IdentifierType getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    long getVersion() {
        return version;
    }
}
