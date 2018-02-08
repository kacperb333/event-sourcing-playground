package pl.kacperb333.java.foodbook.food.composition;

import lombok.Value;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

class IngredientCategory implements DomainEntity<IngredientCategory.Identifier> {
    @Value
    static class Identifier implements UniqueIdentifier<String> {
        private final String id;
    }

    private final Identifier id;
    private final String name;

    private IngredientCategory(String name) {
        this.id = new Identifier(name);
        this.name = name;
    }

    public static IngredientCategory newCategory(String name) {
        notEmpty(name);
        return new IngredientCategory(name);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        IngredientCategory that = (IngredientCategory) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
