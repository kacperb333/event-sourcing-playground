package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.notEmpty;

class IngredientCategory {
    @Value
    static class Identifier {
        private final long id;
    }

    private final Identifier id;
    private final String name;

    private IngredientCategory(Identifier id, String name) {
        this.id = id;
        this.name = name;
    }

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
