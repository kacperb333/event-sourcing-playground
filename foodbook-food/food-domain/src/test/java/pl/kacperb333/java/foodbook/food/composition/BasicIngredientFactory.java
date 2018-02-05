package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.RandomStringUtils;

class BasicIngredientFactory {
    static BasicIngredient ofEnergyValue(EnergyValue energyValue) {
        return ofNameAndEnergyValue("Ingredient " + RandomStringUtils.random(10), energyValue);
    }

    static BasicIngredient ofNameAndEnergyValue(String name, EnergyValue energyValue) {
        return BasicIngredient.create(name, energyValue, BasicIngredient.Category.VEGETABLE);
    }
}
