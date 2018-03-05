package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.List;

public interface EventStore<IdentifierType> {
    void commit(IdentifierType aggregateIdentifier, List<Event> events, long expectedVersion);
    List<Event> getCommittedEvents(IdentifierType aggregateIdentifier);
}
