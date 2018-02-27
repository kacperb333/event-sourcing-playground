package pl.kacperb333.java.foodbook.food.inventory;

import org.springframework.context.ApplicationEventPublisher;
import pl.kacperb333.java.foodbook.eventsourcing.Event;
import pl.kacperb333.java.foodbook.eventsourcing.EventPublisher;

class SpringEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    SpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
