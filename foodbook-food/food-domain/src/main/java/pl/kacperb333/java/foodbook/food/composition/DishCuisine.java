package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;

import java.util.Optional;

import static org.apache.commons.lang3.Validate.*;

class DishCuisine implements DomainEntity<DishCuisine.Identifier> {
    @Value
    static class Identifier implements UniqueIdentifier<Long> {
        private final Long id;
    }

    private final Identifier id;
    private final String name;

    private DishCuisine(Identifier id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
