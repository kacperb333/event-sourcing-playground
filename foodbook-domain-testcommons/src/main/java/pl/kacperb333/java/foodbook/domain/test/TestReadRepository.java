package pl.kacperb333.java.foodbook.domain.test;

import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;
import pl.kacperb333.java.foodbook.domain.repository.DomainReadRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class TestReadRepository<Entity extends DomainEntity>
        implements DomainReadRepository<Entity> {

    protected final AtomicLong sequence = new AtomicLong(0L);
    protected final ConcurrentMap<UniqueIdentifier, Entity> database;

    protected TestReadRepository() {
        this.database = new ConcurrentHashMap<>();
    }

    protected TestReadRepository(Map<? extends UniqueIdentifier, Entity> initialDatabase) {
        this.database = new ConcurrentHashMap<>(initialDatabase);
    }

    @Override
    public Optional<Entity> find(UniqueIdentifier id) {
        return Optional.ofNullable(database.get(id));
    }
}
