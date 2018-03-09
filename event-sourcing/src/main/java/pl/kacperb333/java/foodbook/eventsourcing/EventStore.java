package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.List;

public interface EventStore<IdentifierType> {
    void commit(IdentifierType aggregateIdentifier, List<? extends Event<IdentifierType>> events, long expectedVersion);
    List<Event<IdentifierType>> getCommittedEvents(IdentifierType aggregateIdentifier);
}
