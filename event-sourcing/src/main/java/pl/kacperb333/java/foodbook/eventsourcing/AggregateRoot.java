package pl.kacperb333.java.foodbook.eventsourcing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot<IdentifierType> {
    private final IdentifierType aggregateIdentifier;
    private final List<Event> uncommittedEvents = new LinkedList<>();
    private long version = 0;

    protected AggregateRoot(IdentifierType aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    protected void applyEvent(Event event) {
        applyChange(event);
        uncommittedEvents.add(event);
    }

    void commitEvents(EventStore<IdentifierType> eventStore, long expectedVersion) {
        eventStore.commit(aggregateIdentifier, uncommittedEvents, expectedVersion);
        uncommittedEvents.clear();
    }

    void applyHistory(EventStore<IdentifierType> eventStore) {
        List<Event> existingAggregateEvents = eventStore.getCommittedEvents(aggregateIdentifier);
        if (existingAggregateEvents.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Aggregate (%s) identifier by (%s) does not exist", this, aggregateIdentifier));
        }
        existingAggregateEvents.forEach(this::applyChange);
    }

    long getVersion() {
        return version;
    }

    private void applyChange(Event event) {
        Class<?> eventType = event.getClass();
        try {
            Method method = this.getClass().getDeclaredMethod("apply", eventType);
            method.setAccessible(true);
            method.invoke(this, event);
            version = event.getVersion();
        } catch (SecurityException | IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            throw new UnsupportedOperationException(String.format("Unknown event: %s", event));
        }
    }
}
