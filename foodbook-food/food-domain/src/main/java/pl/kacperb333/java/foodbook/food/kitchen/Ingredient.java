package pl.kacperb333.java.foodbook.food.kitchen;

import pl.kacperb333.java.foodbook.food.kitchen.values.EnergyValue;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

class Ingredient {
    private final String name;
    private final EnergyValue energyValue;

    private Ingredient(String name, EnergyValue energyValue) {
        this.name = name;
        this.energyValue = energyValue;
    }

    public Ingredient ofNameAndEnergyValue(String name, EnergyValue energyValue) {
        notEmpty(name, "Ingredient must have a name");
        notNull(energyValue, "Ingredient must have energy value");
        return new Ingredient(name, energyValue);
    }
}
