package pl.kacperb333.java.foodbook.domain.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kacperb333.java.foodbook.domain.commonexception.EntityAlreadyExistsException;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RepositoryCorrectnessTest {

    private SimpleEntityReadRepository readRepository;
    private SimpleEntityWriteRepository writeRepository;

    @BeforeMethod
    void before() {
        initialize();
    }

    private void initialize(Long... ids) {
        ConcurrentMap<SimpleEntity.Identifier, SimpleEntity> initialDatabase = new ConcurrentHashMap<>();
        for(Long id : ids) {
            SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(id);
            SimpleEntity initialization = new SimpleEntity(identifier);
            initialDatabase.put(initialization.getId(), initialization);
        }

        readRepository = new SimpleEntityReadRepository(initialDatabase);
        writeRepository = new SimpleEntityWriteRepository(initialDatabase);
    }

    @Test
    void addedEntityShouldBeRead() throws EntityAlreadyExistsException {
        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);

        SimpleEntity toSave = new SimpleEntity(identifier);
        writeRepository.tryCreate(toSave);

        Optional<SimpleEntity> read = readRepository.find(identifier);
        Assert.assertTrue(read.isPresent());
    }

    @Test
    void nonexistentEntityShouldNotBeFound() {
        SimpleEntity.Identifier nonexistentIdentifier = new SimpleEntity.Identifier(1L);

        Optional<SimpleEntity> notFound = readRepository.find(nonexistentIdentifier);

        Assert.assertFalse(notFound.isPresent());
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    void shouldThrowExceptionWhenAddingDuplicateEntity() throws EntityAlreadyExistsException {
        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);

        SimpleEntity toInsertTwoTimes = new SimpleEntity(identifier);

        writeRepository.tryCreate(toInsertTwoTimes);
        writeRepository.tryCreate(toInsertTwoTimes);
    }

    @Test
    void shouldReadEntityFromInitializedRepository() {
        initialize(1L);

        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);
        Optional<SimpleEntity> toRead = readRepository.find(identifier);

        Assert.assertTrue(toRead.isPresent());
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    void shouldThrowWhenInsertingEntityAlreadyPresentInInitializedRepository() throws EntityAlreadyExistsException {
        initialize(1L);

        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);
        SimpleEntity toWrite = new SimpleEntity(identifier);

        writeRepository.tryCreate(toWrite);
    }
}
