package pl.kacperb333.java.foodbook.domain.repository;

import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;

import java.util.Optional;

public interface DomainReadRepository<Entity extends DomainEntity, Identifier extends UniqueIdentifier> {
    Identifier provideNewIdentifier();
    Optional<Entity> find(Identifier id);
}
