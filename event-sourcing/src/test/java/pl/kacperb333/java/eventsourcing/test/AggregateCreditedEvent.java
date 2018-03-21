package pl.kacperb333.java.eventsourcing.test;

import pl.kacperb333.java.eventsourcing.Event;

class AggregateCreditedEvent extends Event<SimpleAggregateIdentifier> {

    private final int creditValue;

    protected AggregateCreditedEvent(SimpleAggregateIdentifier aggregateIdentifier, long version, int creditValue) {
        super(aggregateIdentifier, version);
        this.creditValue = creditValue;
    }

    public int getCreditValue() {
        return creditValue;
    }
}
