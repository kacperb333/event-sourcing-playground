package pl.kacperb333.java.foodbook.eventsourcing;

class AggregateActivatedEvent extends Event<SimpleAggregateIdentifier> {
    protected AggregateActivatedEvent(SimpleAggregateIdentifier aggregateIdentifier, long version) {
        super(aggregateIdentifier, version);
    }
}
