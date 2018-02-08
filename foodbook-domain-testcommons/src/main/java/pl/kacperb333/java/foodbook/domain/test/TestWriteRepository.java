package pl.kacperb333.java.foodbook.domain.test;

import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;
import pl.kacperb333.java.foodbook.domain.repository.DomainWriteRepository;

public abstract class TestWriteRepository<Entity extends DomainEntity, Identifier extends UniqueIdentifier>
        implements DomainWriteRepository<Entity> {

    private final TestReadRepository<Entity, Identifier> underlyingReadRepository;

    protected TestWriteRepository(TestReadRepository<Entity, Identifier> underlyingReadRepository) {
        this.underlyingReadRepository = underlyingReadRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized Entity save(Entity toSave) {
        if (underlyingReadRepository.database.values().contains(toSave)) {
            throw new IllegalStateException(
                    String.format("Constraint violation: %s already present in a repository", toSave)
            );
        }

        underlyingReadRepository.database.put((Identifier) toSave.getId(), toSave);
        return toSave;
    }
}
