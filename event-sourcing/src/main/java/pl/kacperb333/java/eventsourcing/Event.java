package pl.kacperb333.java.eventsourcing;

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

    public IdentifierType getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    public long getVersion() {
        return version;
    }
}
