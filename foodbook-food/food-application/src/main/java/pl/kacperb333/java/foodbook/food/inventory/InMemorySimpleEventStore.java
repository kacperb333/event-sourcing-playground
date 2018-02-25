package pl.kacperb333.java.foodbook.food.inventory;

import org.springframework.context.ApplicationEventPublisher;
import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.eventsourcing.EventStore;
import pl.kacperb333.java.foodbook.eventsourcing.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class InMemorySimpleEventStore implements EventStore {

    private List<Event<?>> events = new LinkedList<>();
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
        return events.stream()
                .filter(e -> aggregateIdentifier.equals(e.getAggregateIdentifier()))
                .collect(Collectors.toList());
    }

    private void commitEvent(Event<?> event) {
        events.add(event);
        eventPublisher.publishEvent(event);
    }

}
