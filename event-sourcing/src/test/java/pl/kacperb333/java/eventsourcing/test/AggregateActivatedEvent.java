package pl.kacperb333.java.eventsourcing.test;

import pl.kacperb333.java.eventsourcing.Event;

class AggregateActivatedEvent extends Event<SimpleAggregateIdentifier> {
    protected AggregateActivatedEvent(SimpleAggregateIdentifier aggregateIdentifier, long version) {
        super(aggregateIdentifier, version);
    }
}
