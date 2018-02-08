package pl.kacperb333.java.foodbook.domain.test;

import pl.kacperb333.java.foodbook.domain.commonexception.EntityAlreadyExistsException;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;
import pl.kacperb333.java.foodbook.domain.repository.DomainWriteRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class TestWriteRepository<Entity extends DomainEntity<Identifier>, Identifier extends UniqueIdentifier<?>>
        implements DomainWriteRepository<Entity, Identifier> {

    protected final ConcurrentMap<Identifier, Entity> database;
    protected final AtomicLong sequence = new AtomicLong(0L);

    public TestWriteRepository(ConcurrentMap<Identifier, Entity> database) {
        this.database = database;
    }

    @Override
    public Entity tryCreate(Entity toSave) throws EntityAlreadyExistsException {
        if (database.putIfAbsent(toSave.getId(), toSave) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("Constraint violation: %s already present in a repository", toSave)
            );
        }
        return toSave;
    }
}
