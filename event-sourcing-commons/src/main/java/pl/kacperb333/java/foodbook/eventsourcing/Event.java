package pl.kacperb333.java.foodbook.eventsourcing;

public abstract class Event {
    private final Identifier aggregateIdentifier;

    protected Event(Identifier aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public Identifier getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}
