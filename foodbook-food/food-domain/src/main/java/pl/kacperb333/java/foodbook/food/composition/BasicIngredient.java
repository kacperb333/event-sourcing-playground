package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

class BasicIngredient {
    private final String name;
    private final EnergyValue energyValue;
    private final Category category;

    private BasicIngredient(String name, EnergyValue energyValue, Category category) {
        this.name = name;
        this.energyValue = energyValue;
        this.category = category;
    }

    public static BasicIngredient create(String name, EnergyValue energyValue, Category category) {
        notEmpty(name);
        notNull(energyValue);
        notNull(category);
        return new BasicIngredient(name, energyValue, category);
    }

    public EnergyValue getEnergyValue() {
        return energyValue;
    }

    enum Category {
        MEAT, VEGETABLE, FRUIT
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BasicIngredient that = (BasicIngredient) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(energyValue, that.energyValue)
                .append(category, that.category)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(energyValue)
                .append(category)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("energyValue", energyValue)
                .append("category", category)
                .toString();
    }
}
