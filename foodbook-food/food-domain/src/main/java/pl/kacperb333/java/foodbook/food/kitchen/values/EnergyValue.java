package pl.kacperb333.java.foodbook.food.kitchen.values;

import lombok.Value;

import static org.apache.commons.lang3.Validate.isTrue;

@Value
public class EnergyValue {
    private final int kiloCalories;

    private EnergyValue(int kiloCalories) {
        this.kiloCalories = kiloCalories;
    }

    public static EnergyValue fromKiloCalories(int kiloCalories) {
        isTrue(kiloCalories > 0, "Energy value must be positive");
        return new EnergyValue(kiloCalories);
    }
}
