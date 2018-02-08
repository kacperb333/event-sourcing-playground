package pl.kacperb333.java.foodbook.domain.repository;

import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;

public interface DomainWriteRepository <Entity extends DomainEntity<?>> {
    Entity save(Entity toSave);
}
