package pl.kacperb333.java.foodbook.eventsourcing;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot<IdentifierType> {
    private final List<Event<IdentifierType>> uncommittedEvents = new LinkedList<>();
    private long version = 0;

    public abstract IdentifierType getAggregateIdentifier();

    long getVersion() {
        return version;
    }

    long getNextVersion() {
        return version + 1;
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
        var existingAggregateEvents = eventStore.getCommittedEvents(getAggregateIdentifier());
        if (existingAggregateEvents.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Aggregate (%s) identifier by (%s) does not exist", this, getAggregateIdentifier()));
        }
        existingAggregateEvents.forEach(this::applyChange);
    }

    private void applyChange(Event<?> event) {
        Class<?> eventType = event.getClass();
        try {
            var applyEventMethod = MethodHandles.lookup().findVirtual(
                    this.getClass(), "apply", MethodType.methodType(void.class, eventType));
            applyEventMethod.invokeWithArguments(this, event);
            version = event.getVersion();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }
}
