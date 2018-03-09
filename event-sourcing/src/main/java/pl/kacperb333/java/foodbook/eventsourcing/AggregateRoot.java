package pl.kacperb333.java.foodbook.eventsourcing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot<IdentifierType> {
    private final List<Event<IdentifierType>> uncommittedEvents = new LinkedList<>();
    private long version = 0;

    public abstract IdentifierType getAggregateIdentifier();

    long getVersion() {
        return version;
    }

    protected void applyEvent(Event<IdentifierType> event) {
        applyChange(event);
        uncommittedEvents.add(event);
    }

    void commitEvents(EventStore<IdentifierType> eventStore, long expectedVersion) {
        eventStore.commit(getAggregateIdentifier(), uncommittedEvents, expectedVersion);
        uncommittedEvents.clear();
    }

    void applyHistory(EventStore<IdentifierType> eventStore) {
        List<Event<IdentifierType>> existingAggregateEvents = eventStore.getCommittedEvents(getAggregateIdentifier());
        if (existingAggregateEvents.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Aggregate (%s) identifier by (%s) does not exist", this, getAggregateIdentifier()));
        }
        existingAggregateEvents.forEach(this::applyChange);
    }

    private void applyChange(Event event) {
        Class<?> eventType = event.getClass();
        try {
            Method method = this.getClass().getDeclaredMethod("apply", eventType);
            method.setAccessible(true);
            method.invoke(this, event);
            version = event.getVersion() + 1;
        } catch (SecurityException | IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            throw new UnsupportedOperationException(String.format("Unknown event: %s", event));
        }
    }
}
