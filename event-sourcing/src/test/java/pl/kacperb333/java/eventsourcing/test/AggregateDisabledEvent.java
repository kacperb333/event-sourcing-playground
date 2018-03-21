package pl.kacperb333.java.eventsourcing.test;

import pl.kacperb333.java.eventsourcing.Event;

class AggregateDisabledEvent extends Event<SimpleAggregateIdentifier> {
    protected AggregateDisabledEvent(SimpleAggregateIdentifier aggregateIdentifier, long version) {
        super(aggregateIdentifier, version);
    }
}
