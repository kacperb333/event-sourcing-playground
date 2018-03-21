package pl.kacperb333.java.eventsourcing;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleAggregateRootTest {

    private InMemoryRepository<SimpleAggregateRoot, SimpleAggregateIdentifier> simpleAggregateRepository;
    private InMemoryEventStore<SimpleAggregateIdentifier> eventStore;

    @BeforeMethod
    void beforeMethod() {
        eventStore = new InMemoryEventStore<>(new DevNullEventPublisher());
        simpleAggregateRepository = new InMemoryRepository<>(SimpleAggregateRoot.class, eventStore);
    }

    @Test
    void loadedAggregateShouldHaveCorrectStateAfterSaving() throws AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        var loadedAggregate = simpleAggregateRepository.load(identifier);

        assertAggregate(identifier, loadedAggregate);
    }

    @Test
    void shouldLoadAggregateWithExactExpectedVersion() throws NoExactResultException, AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        var loaded = simpleAggregateRepository.loadExact(identifier, 4L);
        assertAggregate(identifier, loaded);
    }

    @Test(expectedExceptions = NoExactResultException.class)
    void shouldThrowExceptionWhenLoadingWithTooLittleExactVersion() throws NoExactResultException, AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        simpleAggregateRepository.loadExact(identifier, 3L);
    }

    @Test(expectedExceptions = NoExactResultException.class)
    void shouldThrowExceptionWhenLoadingWithTooGreatExactVersion() throws NoExactResultException, AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        simpleAggregateRepository.loadExact(identifier, 5L);
    }

    @Test
    void shouldLoadAggregateWithExactLeastExpectedVersion() throws NoExpectedResultException, AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        var loaded = simpleAggregateRepository.loadAtLeast(identifier, 4L);
        assertAggregate(identifier, loaded);
    }

    @Test
    void shouldLoadAggregateWithLeastExpectedVersionLessThanActual() throws NoExpectedResultException, AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        var loaded = simpleAggregateRepository.loadAtLeast(identifier, 3L);
        assertAggregate(identifier, loaded);
    }

    @Test(expectedExceptions = NoExpectedResultException.class)
    void shouldThrowExceptionWhenLoadingWithLeastExpectedVersionGreaterThanActual() throws NoExpectedResultException, AggregateNotFoundException {
        var identifier = new SimpleAggregateIdentifier("ABC");
        var aggregate = provideAggregate(identifier);

        simpleAggregateRepository.save(aggregate);
        simpleAggregateRepository.loadAtLeast(identifier, 5L);
    }

    private static final String INITIAL_NAME = "name";
    private static final String CHANGED_NAME = "some new name";
    private static final int INITIAL_BALANCE = 50;
    private static final int DEBIT_AMOUNT = 20;
    private static final int CREDIT_AMOUNT = 100;

    private static final long EXPECTED_VERSION = 4L;

    private SimpleAggregateRoot provideAggregate(SimpleAggregateIdentifier identifier) {
        var aggregate = SimpleAggregateRoot.startProcess(identifier, INITIAL_NAME, INITIAL_BALANCE);
        aggregate.rename(CHANGED_NAME);
        aggregate.debit(DEBIT_AMOUNT);
        aggregate.credit(CREDIT_AMOUNT);
        aggregate.disable();
        return aggregate;
    }

    private void assertAggregate(SimpleAggregateIdentifier identifier, SimpleAggregateRoot aggregate) {
        assertEquals(eventStore.getCommittedEvents(identifier).size(), EXPECTED_VERSION + 1);
        assertEquals(aggregate.getIdentifier(), identifier);
        assertEquals(aggregate.getVersion(), EXPECTED_VERSION);
        assertEquals(aggregate.getBalance(), INITIAL_BALANCE - DEBIT_AMOUNT + CREDIT_AMOUNT);
        assertEquals(aggregate.getName(), CHANGED_NAME);
        assertEquals(aggregate.getState(), SimpleAggregateRoot.State.DISABLED);
    }
}
