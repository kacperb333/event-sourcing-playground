package pl.kacperb333.java.eventsourcing;

class DevNullEventPublisher implements EventPublisher {
    @Override
    public void publish(Event<?> event) {

    }
}
