package pl.kacperb333.java.foodbook.eventsourcing;

public abstract class Event {
    private final Identifier aggregateIdentifier;
    private long version;

    protected Event(Identifier aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public Identifier getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    long getVersion() {
        return version;
    }

    void setVersion(long version) {
        this.version = version;
    }
}
