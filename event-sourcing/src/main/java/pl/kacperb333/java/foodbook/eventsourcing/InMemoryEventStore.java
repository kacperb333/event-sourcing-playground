package pl.kacperb333.java.foodbook.eventsourcing;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryEventStore<IdentifierType> implements EventStore<IdentifierType> {

    private final ConcurrentMap<IdentifierType, List<Event<IdentifierType>>> events = new ConcurrentHashMap<>();
    private final EventPublisher eventPublisher;

    public InMemoryEventStore(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void commit(IdentifierType aggregateIdentifier, List<? extends Event<IdentifierType>> eventsToCommit, long expectedVersion) {
        List<Event<IdentifierType>> currentEvents = events.getOrDefault(aggregateIdentifier, new LinkedList<>());
        events.putIfAbsent(aggregateIdentifier, currentEvents);

        if (!currentEvents.isEmpty() && currentEvents.get(currentEvents.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrentModificationException();
        }

        List<Event<IdentifierType>> newEvents = new LinkedList<>(currentEvents);
        newEvents.addAll(eventsToCommit);
        if (!events.replace(aggregateIdentifier, currentEvents, newEvents)) {
            throw new ConcurrentModificationException();
        }

        publishEvents(eventsToCommit);
    }

    private void publishEvents(List<? extends Event<IdentifierType>> eventsToPublish) {
        eventsToPublish.forEach(eventPublisher::publish);
    }

    @Override
    public List<Event<IdentifierType>> getCommittedEvents(IdentifierType aggregateIdentifier) {
        return events.get(aggregateIdentifier);
    }
}
