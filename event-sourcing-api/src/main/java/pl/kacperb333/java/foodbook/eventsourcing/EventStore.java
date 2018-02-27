package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.List;

public interface EventStore {
    void commit(List<Event<?>> events);
    List<Event<?>> getCommittedEvents(Identifier aggregateIdentifier);
}
