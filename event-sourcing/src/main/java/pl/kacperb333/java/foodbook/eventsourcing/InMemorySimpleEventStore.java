package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemorySimpleEventStore implements EventStore {

    private final ConcurrentMap<Identifier, List<Event>> events = new ConcurrentHashMap<>();
    private final EventPublisher eventPublisher;

    public InMemorySimpleEventStore(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void commit(Identifier aggregateIdentifier, List<Event> eventsToCommit, long expectedVersion) {
        List<Event> currentEvents = events.getOrDefault(aggregateIdentifier, new LinkedList<>());
        events.putIfAbsent(aggregateIdentifier, currentEvents);

        if (!currentEvents.isEmpty() && currentEvents.get(currentEvents.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrentModificationException();
        }

        List<Event> newEvents = new LinkedList<>(currentEvents);
        newEvents.addAll(eventsToCommit);
        if (!events.replace(aggregateIdentifier, currentEvents, newEvents)) {
            throw new ConcurrentModificationException();
        }

        publishEvents(eventsToCommit);
    }

    private void publishEvents(List<Event> eventsToPublish) {
        eventsToPublish.forEach(eventPublisher::publish);
    }

    @Override
    public List<Event> getCommittedEvents(Identifier aggregateIdentifier) {
        return events.get(aggregateIdentifier);
    }
}
