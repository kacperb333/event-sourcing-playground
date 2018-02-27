package pl.kacperb333.java.foodbook.eventsourcing;

public interface EventPublisher {
    void publish(Event event);
}
