package pl.kacperb333.java.foodbook.domain.commontype;

public interface DomainEntity<Identifier extends UniqueIdentifier<?>> {
    Identifier getId();
}
