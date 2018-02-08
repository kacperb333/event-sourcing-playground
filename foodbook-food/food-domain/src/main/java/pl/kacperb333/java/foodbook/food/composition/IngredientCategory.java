package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import org.apache.commons.lang3.Validate;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;

import static org.apache.commons.lang3.Validate.notEmpty;

class IngredientCategory implements DomainEntity<IngredientCategory.Identifier> {
    @Value
    static class Identifier implements UniqueIdentifier<Long> {
        private final Long id;
    }

    private final Identifier id;
    private final String name;

    private IngredientCategory(Identifier id, String name) {
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

    static class Provider {
        private final IngredientCategoryReadRepository repository;

        public Provider(IngredientCategoryReadRepository repository) {
            this.repository = repository;
        }

        public IngredientCategory createFrom(String name) {
            notEmpty(name);
            Validate.isTrue(!repository.existsByName(name), "Ingredient category %s already exists", name);
            return new IngredientCategory(repository.provideNewIdentifier(), name);
        }
    }
}
