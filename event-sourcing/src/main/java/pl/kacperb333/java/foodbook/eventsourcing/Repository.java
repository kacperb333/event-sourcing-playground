package pl.kacperb333.java.foodbook.eventsourcing;

public interface Repository<AggregateType extends AggregateRoot<IdentifierType>, IdentifierType> {
    void save(AggregateType toSave, long expectedVersion);

    AggregateType load(IdentifierType aggregateIdentifier);
    AggregateType loadExact(IdentifierType aggregateIdentifier, long expectedVersion)
            throws NoExactResultException;
    AggregateType loadAtLeast(IdentifierType aggregateIdentifier, long leastExpectedVersion)
            throws NoExpectedResultException;
}
