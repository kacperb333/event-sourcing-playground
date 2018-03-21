package pl.kacperb333.java.eventsourcing.test;

import pl.kacperb333.java.eventsourcing.Event;

class AggregateProcessStartedEvent extends Event<SimpleAggregateIdentifier> {

    private final String name;
    private final int initialBalance;

    protected AggregateProcessStartedEvent(SimpleAggregateIdentifier aggregateIdentifier,
                                           String name, int initialBalance) {
        super(aggregateIdentifier);
        this.name = name;
        this.initialBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public int getInitialBalance() {
        return initialBalance;
    }
}
