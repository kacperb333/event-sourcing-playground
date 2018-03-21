package pl.kacperb333.java.foodbook.eventsourcing;

class AggregateNotFoundException extends Exception {
    private final Class<?> aggregateType;
    private final Object aggregateIdentifier;

    public AggregateNotFoundException(Class<?> aggregateType, Object aggregateIdentifier) {
        super(String.format("Aggregate of type %s, identified by %s does not exist", aggregateType, aggregateIdentifier));
        this.aggregateType = aggregateType;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public Class<?> getAggregateType() {
        return aggregateType;
    }

    public Object getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}
