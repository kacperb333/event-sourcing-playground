package pl.kacperb333.java.eventsourcing;

import org.apache.commons.lang3.exception.ExceptionUtils;

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

    void applyExistingEvent(Event<IdentifierType> event) {
        applyChange(event);
    }

    void commitEvents(EventStore<IdentifierType> eventStore, long expectedVersion) {
        eventStore.commit(getAggregateIdentifier(), uncommittedEvents, expectedVersion);
        uncommittedEvents.clear();
    }

    private void applyChange(Event<?> event) {
        Class<?> eventType = event.getClass();
        try {
            var applyEventMethod = MethodHandles.lookup().findVirtual(
                    this.getClass(), "apply", MethodType.methodType(void.class, eventType));
            applyEventMethod.invokeWithArguments(this, event);
            version = event.getVersion();
        } catch (Throwable ex) {
            ExceptionUtils.rethrow(ex);
        }
    }
}
