package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

import static org.apache.commons.lang3.Validate.notEmpty;

class BasicDish {
    private final String name;
    private final Set<BasicIngredient> ingredients;

    private BasicDish(String name, Set<BasicIngredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public static BasicDish create(String name, Set<BasicIngredient> ingredients) {
        notEmpty(name);
        notEmpty(ingredients);
        return new BasicDish(name, ingredients);
    }

    public EnergyValue energyValue() {
        return ingredients.stream()
                .map(BasicIngredient::getEnergyValue)
                .reduce(EnergyValue.ofKcal(0), EnergyValue::plus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BasicDish basicDish = (BasicDish) o;

        return new EqualsBuilder()
                .append(name, basicDish.name)
                .append(ingredients, basicDish.ingredients)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(ingredients)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("ingredients", ingredients)
                .toString();
    }
}
