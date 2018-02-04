package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

class EnergyValue {
    private final int kcalValue;

    private EnergyValue(int kcalValue) {
        this.kcalValue = kcalValue;
    }

    public static EnergyValue ofKcal(int kcal) {
        Validate.isTrue(kcal >= 0, "Negative energy value is not valid");
        return new EnergyValue(kcal);
    }

    public int kcalValue() {
        return kcalValue;
    }

    public EnergyValue plus(EnergyValue toAdd) {
        return new EnergyValue(kcalValue + toAdd.kcalValue);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("kcalValue", kcalValue)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EnergyValue that = (EnergyValue) o;

        return new EqualsBuilder()
                .append(kcalValue, that.kcalValue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(kcalValue)
                .toHashCode();
    }
}
