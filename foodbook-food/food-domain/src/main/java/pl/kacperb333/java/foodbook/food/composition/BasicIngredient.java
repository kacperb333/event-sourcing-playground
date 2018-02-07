package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;


class BasicIngredient {
    private final Identifier id;
    private final String name;
    private final EnergyValue energyValue;
    private final Category category;

    BasicIngredient(Identifier id, String name, EnergyValue energyValue, Category category) {
        this.id = id;
        this.name = name;
        this.energyValue = energyValue;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public EnergyValue getEnergyValue() {
        return energyValue;
    }

    enum Category {
        MEAT, VEGETABLE, FRUIT
    }

    static class Identifier {
        private final long id;

        private Identifier(long id) {
            this.id = id;
        }

        public static Identifier from(long id) {
            return new Identifier(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Identifier that = (Identifier) o;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", id)
                    .toString();
        }
    }

    static class Provider {

        private final IngredientRepository repository;

        public Provider(IngredientRepository repository) {
            this.repository = repository;
        }

        public BasicIngredient create(String name, EnergyValue energyValue, Category category) {
            notEmpty(name);
            notNull(energyValue);
            notNull(category);
            return new BasicIngredient(repository.provideNewIdentifier(), name, energyValue, category);
        }

        public Optional<BasicIngredient> existingById(Identifier id) {
            notNull(id);
            return repository.find(id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BasicIngredient that = (BasicIngredient) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(energyValue, that.energyValue)
                .append(category, that.category)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(energyValue)
                .append(category)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("energyValue", energyValue)
                .append("category", category)
                .toString();
    }
}
