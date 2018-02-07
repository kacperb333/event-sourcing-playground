package pl.kacperb333.java.foodbook.domain.test;

import pl.kacperb333.java.foodbook.domain.repository.DomainReadRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class TestReadRepository<Entity, Identifier> implements DomainReadRepository<Entity, Identifier> {
    protected final AtomicLong sequence = new AtomicLong(0L);
    protected final ConcurrentMap<Identifier, Entity> database;

    protected TestReadRepository() {
        this.database = new ConcurrentHashMap<>();
    }

    protected TestReadRepository(Map<Identifier, Entity> initialDatabase) {
        this.database = new ConcurrentHashMap<>(initialDatabase);
    }

    @Override
    public Optional<Entity> find(Identifier id) {
        return Optional.ofNullable(database.get(id));
    }
}
