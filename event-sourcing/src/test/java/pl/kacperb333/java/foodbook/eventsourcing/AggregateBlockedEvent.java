package pl.kacperb333.java.foodbook.eventsourcing;

class AggregateBlockedEvent extends Event<SimpleAggregateIdentifier> {
    protected AggregateBlockedEvent(SimpleAggregateIdentifier aggregateIdentifier, long version) {
        super(aggregateIdentifier, version);
    }
}
