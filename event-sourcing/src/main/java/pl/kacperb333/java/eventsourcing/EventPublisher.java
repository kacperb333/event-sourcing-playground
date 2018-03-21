package pl.kacperb333.java.eventsourcing;

public interface EventPublisher {
    void publish(Event<?> event);
}
