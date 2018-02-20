package pl.kacperb333.java.foodbook.food.inventory;

import org.springframework.context.ApplicationEventPublisher;
import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.eventsourcing.EventStore;
import pl.kacperb333.java.foodbook.eventsourcing.Identifier;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class InMemorySimpleEventStore implements EventStore {

    private Map<Identifier, List<Event<?>>> events = new HashMap<>();
    private final ApplicationEventPublisher eventPublisher;

    InMemorySimpleEventStore(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void commit(List<Event<?>> events) {
        events.forEach(this::commitEvent);
    }

    @Override
    public List<Event<?>> getCommittedEvents(Identifier aggregateIdentifier) {
        return events.getOrDefault(aggregateIdentifier, new LinkedList<>());
    }

    private void commitEvent(Event<?> event) {
        List<Event<?>> committedEvents = events.getOrDefault(event.getAggregateIdentifier(), new LinkedList<>());
        committedEvents.add(event);
        events.put(event.getAggregateIdentifier(), committedEvents);
        eventPublisher.publishEvent(event);
    }

}
