package pl.kacperb333.java.foodbook.domain.repository;

import pl.kacperb333.java.foodbook.domain.commonexception.EntityAlreadyExistsException;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;

public interface DomainWriteRepository <Entity extends DomainEntity<Identifier>, Identifier extends UniqueIdentifier<?>> {
    Entity tryCreate(Entity toSave) throws EntityAlreadyExistsException;
}
