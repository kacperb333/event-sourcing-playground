package pl.kacperb333.java.eventsourcing;

class AggregateDisabledEvent extends Event<SimpleAggregateIdentifier> {
    protected AggregateDisabledEvent(SimpleAggregateIdentifier aggregateIdentifier, long version) {
        super(aggregateIdentifier, version);
    }
}
