package pl.kacperb333.java.foodbook.eventsourcing;

class AggregateDebitedEvent extends Event<SimpleAggregateIdentifier> {

    private final int debitValue;

    protected AggregateDebitedEvent(SimpleAggregateIdentifier aggregateIdentifier, long version, int debitValue) {
        super(aggregateIdentifier, version);
        this.debitValue = debitValue;
    }

    public int getDebitValue() {
        return debitValue;
    }
}
