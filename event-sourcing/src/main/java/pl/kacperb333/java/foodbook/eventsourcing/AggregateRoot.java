package pl.kacperb333.java.foodbook.eventsourcing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot<T extends Identifier> {
    private final T identifier;
    private final List<Event> uncommittedEvents = new LinkedList<>();
    private long version = 0;

    protected AggregateRoot(T identifier) {
        this.identifier = identifier;
    }

    public T getIdentifier() {
        return identifier;
    }

    protected void applyEvent(Event event) {
        event.setVersion(++version);
        applyChange(event);
        uncommittedEvents.add(event);
    }

    void commitEvents(EventStore eventStore, long expectedVersion) {
        eventStore.commit(identifier, uncommittedEvents, expectedVersion);
        uncommittedEvents.clear();
    }

    void applyHistory(EventStore eventStore) {
        Identifier aggregateRootIdentifier = getIdentifier();
        List<Event> existingAggregateEvents = eventStore.getCommittedEvents(aggregateRootIdentifier);
        if (existingAggregateEvents.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Aggregate (%s) identifier by (%s) does not exist", this, aggregateRootIdentifier));
        }
        existingAggregateEvents.forEach(this::applyChange);
    }

    public long getVersion() {
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
