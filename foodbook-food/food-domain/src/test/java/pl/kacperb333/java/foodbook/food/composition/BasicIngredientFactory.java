package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.RandomStringUtils;

class BasicIngredientFactory {
    static BasicIngredient ofEnergyValue(Long ordinal, EnergyValue energyValue) {
        return ofNameAndEnergyValue(ordinal, "Ingredient " + RandomStringUtils.random(10), energyValue);
    }

    static BasicIngredient ofNameAndEnergyValue(Long ordinal, String name, EnergyValue energyValue) {
        return new BasicIngredient(BasicIngredient.Identifier.from(ordinal), name, energyValue,
                BasicIngredient.Category.VEGETABLE);
    }
}
