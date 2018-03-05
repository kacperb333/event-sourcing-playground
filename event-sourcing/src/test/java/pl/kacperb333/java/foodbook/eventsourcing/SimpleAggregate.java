package pl.kacperb333.java.foodbook.eventsourcing;

class SimpleAggregate extends AggregateRoot<SimpleAggregateIdentifier> {

    enum State {
        ACTIVE, DISABLED
    }

    private String name;
    private int accumulator;
    private State state;

    private SimpleAggregate(SimpleAggregateIdentifier aggregateIdentifier) {
        super(aggregateIdentifier);
    }

}
