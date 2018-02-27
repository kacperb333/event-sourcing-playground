package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.List;

public interface EventStore {
    void commit(Identifier aggregateIdentifier, List<Event> events, long expectedVersion);
    List<Event> getCommittedEvents(Identifier aggregateIdentifier);
}
