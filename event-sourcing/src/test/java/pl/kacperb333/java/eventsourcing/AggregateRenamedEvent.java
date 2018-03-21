package pl.kacperb333.java.eventsourcing;

class AggregateRenamedEvent extends Event<SimpleAggregateIdentifier> {

    private final String newName;

    protected AggregateRenamedEvent(SimpleAggregateIdentifier aggregateIdentifier, long version, String newName) {
        super(aggregateIdentifier, version);
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
