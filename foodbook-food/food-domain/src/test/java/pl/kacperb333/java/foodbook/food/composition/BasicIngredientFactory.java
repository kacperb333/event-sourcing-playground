package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.RandomStringUtils;

class BasicIngredientFactory {
    static BasicIngredient ofEnergyValue(EnergyValue energyValue) {
        return BasicIngredient.create("Ingredient " + RandomStringUtils.random(10), energyValue,
                BasicIngredient.Category.VEGETABLE);
    }
}
