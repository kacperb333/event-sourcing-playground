package pl.kacperb333.java.eventsourcing.test;

import pl.kacperb333.java.eventsourcing.Event;
import pl.kacperb333.java.eventsourcing.EventPublisher;

class DevNullEventPublisher implements EventPublisher {
    @Override
    public void publish(Event<?> event) {

    }
}
