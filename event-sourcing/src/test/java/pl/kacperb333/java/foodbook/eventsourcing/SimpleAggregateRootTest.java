package pl.kacperb333.java.foodbook.eventsourcing;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleAggregateRootTest {

    private InMemoryRepository<SimpleAggregateRoot, SimpleAggregateIdentifier> simpleAggregateRepository;
    private InMemoryEventStore<SimpleAggregateIdentifier> eventStore;

    @BeforeMethod
    void beforeMethod() {
        eventStore = new InMemoryEventStore<>(new DevNullEventPublisher());
        simpleAggregateRepository = new InMemoryRepository<>(SimpleAggregateRoot.class, eventStore);
    }

    @Test
    void smokeTest() {
        SimpleAggregateIdentifier identifier = new SimpleAggregateIdentifier("ABC");
        SimpleAggregateRoot aggregate = SimpleAggregateRoot.startProcess(identifier, "name", 50);
        aggregate.rename("some new name");
        aggregate.debit(20);
        aggregate.credit(100);
        aggregate.disable();
        simpleAggregateRepository.save(aggregate, 0);

        SimpleAggregateRoot loadedAggregate = simpleAggregateRepository.load(identifier);

        Assert.assertEquals(aggregate, loadedAggregate);
        Assert.assertEquals(eventStore.getCommittedEvents(identifier).size(), 5);
        Assert.assertEquals(aggregate.getVersion(), loadedAggregate.getVersion());
        Assert.assertEquals(aggregate.getVersion(), 5);
    }
}
