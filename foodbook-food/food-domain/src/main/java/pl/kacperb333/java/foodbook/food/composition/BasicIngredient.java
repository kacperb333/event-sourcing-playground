package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import pl.kacperb333.java.foodbook.domain.commonvalue.Money;

import java.util.Optional;

import static org.apache.commons.lang3.Validate.*;


class BasicIngredient {
    @Value
    static class Identifier {
        private final long id;
    }

    private final Identifier id;
    private final String name;
    private final EnergyValue energyValue;
    private final Money price;
    private final IngredientCategory category;

    private BasicIngredient(Identifier id, String name, EnergyValue energyValue, Money price, IngredientCategory category) {
        this.id = id;
        this.name = name;
        this.energyValue = energyValue;
        this.price = price;
        this.category = category;
    }

    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EnergyValue getEnergyValue() {
        return energyValue;
    }

    public Money getPrice() {
        return price;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    static class Provider {

        private final IngredientReadRepository repository;

        public Provider(IngredientReadRepository repository) {
            this.repository = repository;
        }

        public BasicIngredient createFrom(String name, EnergyValue energyValue, Money price, IngredientCategory category) {
            notEmpty(name);
            notNull(energyValue);
            notNull(category);
            isTrue(!repository.existsByName(name), "Ingredient %s already exists", name);
            return new BasicIngredient(repository.provideNewIdentifier(), name, energyValue, price, category);
        }

        public Optional<BasicIngredient> find(Identifier id) {
            notNull(id);
            return repository.find(id);
        }
    }
}
