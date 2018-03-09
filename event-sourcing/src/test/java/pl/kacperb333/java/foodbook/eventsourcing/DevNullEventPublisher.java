package pl.kacperb333.java.foodbook.eventsourcing;

class DevNullEventPublisher implements EventPublisher {
    @Override
    public void publish(Event<?> event) {

    }
}
