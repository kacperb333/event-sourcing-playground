package pl.kacperb333.java.foodbook.domain.repository;

import java.util.Optional;

public interface DomainReadRepository<Entity, Identifier> {
    Identifier provideNewIdentifier();
    Optional<Entity> find(Identifier id);
}
