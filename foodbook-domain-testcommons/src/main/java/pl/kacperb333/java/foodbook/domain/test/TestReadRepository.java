package pl.kacperb333.java.foodbook.domain.test;

import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;
import pl.kacperb333.java.foodbook.domain.repository.DomainReadRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class TestReadRepository<Entity extends DomainEntity<Identifier>, Identifier extends UniqueIdentifier<?>>
        implements DomainReadRepository<Entity, Identifier> {

    protected final ConcurrentMap<Identifier, Entity> database;

    protected TestReadRepository(ConcurrentMap<Identifier, Entity> database) {
        this.database = database;
    }

    @Override
    public boolean exists(Identifier id) {
        return find(id).isPresent();
    }

    @Override
    public Optional<Entity> find(Identifier id) {
        return Optional.ofNullable(database.get(id));
    }
}
