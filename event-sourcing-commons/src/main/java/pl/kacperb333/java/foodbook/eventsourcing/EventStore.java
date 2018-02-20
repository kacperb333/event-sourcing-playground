package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.List;

public interface EventStore {
    void commit(List<Event> event);
    List<Event> getCommittedEvents(Identifier aggregateIdentifier);
}
