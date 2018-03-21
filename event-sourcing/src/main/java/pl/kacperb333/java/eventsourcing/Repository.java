package pl.kacperb333.java.eventsourcing;

public interface Repository<AggregateType extends AggregateRoot<IdentifierType>, IdentifierType> {
    default void save(AggregateType toSave) { save(toSave, 0L); }
    void save(AggregateType toSave, long expectedVersion);

    AggregateType load(IdentifierType aggregateIdentifier) throws AggregateNotFoundException;
    AggregateType loadExact(IdentifierType aggregateIdentifier, long expectedVersion)
            throws AggregateNotFoundException, NoExactResultException;
    AggregateType loadAtLeast(IdentifierType aggregateIdentifier, long leastExpectedVersion)
            throws AggregateNotFoundException, NoExpectedResultException;
}
