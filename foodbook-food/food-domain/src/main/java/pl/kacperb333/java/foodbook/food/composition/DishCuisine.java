package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;

import java.util.Optional;

import static org.apache.commons.lang3.Validate.*;

class DishCuisine {
    @Value
    static class Identifier {
        private final long id;
    }

    private final Identifier id;
    private final String name;

    private DishCuisine(Identifier id, String name) {
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
        private final DishCuisineReadRepository repositotry;

        public Provider(DishCuisineReadRepository repositotry) {
            this.repositotry = repositotry;
        }

        Optional<DishCuisine> find(Identifier id) {
            notNull(id);
            return repositotry.find(id);
        }

        DishCuisine createFrom(String name) {
            notEmpty(name);
            isTrue(!repositotry.existsByName(name), "Cuisine %s already exists", name);
            return new DishCuisine(repositotry.provideNewIdentifier(), name);
        }
    }
}
