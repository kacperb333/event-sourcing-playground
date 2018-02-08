package pl.kacperb333.java.foodbook.domain.test;

import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;
import pl.kacperb333.java.foodbook.domain.repository.DomainWriteRepository;

public abstract class TestWriteRepository<Entity extends DomainEntity> implements DomainWriteRepository<Entity> {

    private final TestReadRepository<Entity> underlyingReadRepository;

    protected TestWriteRepository(TestReadRepository<Entity> underlyingReadRepository) {
        this.underlyingReadRepository = underlyingReadRepository;
    }

    @Override
    public synchronized Entity save(Entity toSave) {
        if (underlyingReadRepository.database.values().contains(toSave)) {
            throw new IllegalStateException(
                    String.format("Constraint violation: %s already present in a repository", toSave)
            );
        }
        underlyingReadRepository.database.put(toSave.getId(), toSave);
        return toSave;
    }
}
