package pl.kacperb333.java.foodbook.domain.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kacperb333.java.foodbook.domain.commonexception.EntityAlreadyExistsException;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.testng.Assert.assertTrue;

public class RepositoryConcurrencyTest {
    private SimpleEntityWriteRepository writeRepository;

    @BeforeMethod
    void before() {
        initialize();
    }

    private void initialize(Long... ids) {
        ConcurrentMap<SimpleEntity.Identifier, SimpleEntity> initialDatabase = new ConcurrentHashMap<>();
        for (Long id : ids) {
            SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(id);
            SimpleEntity initialization = new SimpleEntity(identifier);
            initialDatabase.put(initialization.getId(), initialization);
        }

        writeRepository = new SimpleEntityWriteRepository(initialDatabase);
    }

    @Test
    void concurrentInvocationsShouldThrowExceptionInCaseOfaConflict() {
        for (long i = 0; i < 10000; ++i) {
            SimpleEntity toCreate = new SimpleEntity(new SimpleEntity.Identifier(i));
            CyclicBarrier barrier = new CyclicBarrier(2);

            final AtomicBoolean thrown = new AtomicBoolean(false);
            Runnable createEntityTask = () -> {
                try {
                    barrier.await();
                    writeRepository.tryCreate(toCreate);
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new AssertionError("Barrier error");
                } catch (EntityAlreadyExistsException e) {
                    if (!thrown.compareAndSet(false, true)) {
                        throw new AssertionError("Both tasks failed");
                    }
                }
            };

            Thread firstTask = new Thread(createEntityTask);
            Thread secondTask = new Thread(createEntityTask);
            firstTask.start();
            secondTask.start();

            try {
                firstTask.join();
                secondTask.join();
            } catch (InterruptedException e) {
                throw new AssertionError("Task threads join interrupted");
            }

            assertTrue(thrown.get(), String.format("Iteration: %d", i));
        }
    }
}
