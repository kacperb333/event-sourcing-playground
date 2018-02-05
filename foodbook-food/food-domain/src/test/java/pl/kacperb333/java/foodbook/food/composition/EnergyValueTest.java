package pl.kacperb333.java.foodbook.food.composition;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class EnergyValueTest {

    @Test
    public void energyValueOfDishShouldBeEqualToEnergyValuesOfIngredients() {
        int ingredient1kcal = 100;
        int ingredient2kcal = 150;
        int ingredient3kcal = 250;

        BasicDish dish = composeDishOfKcal(ingredient1kcal, ingredient2kcal, ingredient3kcal);

        Assert.assertEquals(ingredient1kcal + ingredient2kcal + ingredient3kcal, dish.energyValue().kcalValue());
    }

    private BasicDish composeDishOfKcal(int... kcalValues) {
        Set<BasicIngredient> ingredients = new HashSet<>();
        for (int kcal : kcalValues) {
            EnergyValue ingredientEnergyValue = EnergyValue.ofKcal(kcal);
            BasicIngredient ingredient = BasicIngredientFactory.ofEnergyValue(ingredientEnergyValue);
            ingredients.add(ingredient);
        }
        return BasicDishFactory.ofIngredients(ingredients);
    }
}
