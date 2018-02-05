package pl.kacperb333.java.foodbook.food.composition;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class IngredientPreparerTest {

    @Test
    public void preparingShouldAddEnergyValueProperly() {
        int ingredientKcal = 100;
        int cookingKcal = 100;
        int roastingKcal = 250;

        Preparable someIngredient = BasicIngredientFactory.ofEnergyValue(1L, EnergyValue.ofKcal(100));
        Preparable cookedAndRoasted = cookAndRoast(someIngredient, cookingKcal, roastingKcal);

        assertEquals(EnergyValue.ofKcal(ingredientKcal + cookingKcal + roastingKcal), cookedAndRoasted.getEnergyValue());
    }

    @Test
    public void preparingShouldAddProperNamePrefix() {
        String potatoName = "Potato";

        Preparable potato = BasicIngredientFactory.ofNameAndEnergyValue(1L, potatoName, EnergyValue.ofKcal(100));
        Preparable roastedAndCookedPotato = cookAndRoast(potato, 100, 200);

        assertEquals("Roasted Cooked Potato", roastedAndCookedPotato.getName());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Overroasted")
    public void sameImmediatePreparationShouldThrowException() {
        Preparable someIngredient = BasicIngredientFactory.ofEnergyValue(1L, EnergyValue.ofKcal(100));

        Preparable roastedAndCooked = cookAndRoast(someIngredient, 100, 100);
        Preparers.roast(roastedAndCooked, EnergyValue.ofKcal(100));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Overcooked")
    public void samePreparationAsOneOfPreviousShouldThrowException() {
        Preparable someIngredient = BasicIngredientFactory.ofEnergyValue(1L, EnergyValue.ofKcal(100));

        Preparable roastedAndCooked = cookAndRoast(someIngredient, 100, 100);
        Preparers.cook(roastedAndCooked, EnergyValue.ofKcal(100));
    }

    private Preparable cookAndRoast(Preparable toPrepare, int cookingKcal, int roastingKcal) {
        EnergyValue cookingEnergyValue = EnergyValue.ofKcal(cookingKcal);
        EnergyValue roastingEnergyValue = EnergyValue.ofKcal(roastingKcal);

        Preparable cooked = Preparers.cook(toPrepare, cookingEnergyValue);
        Preparable roastedAndCooked = Preparers.roast(cooked, roastingEnergyValue);

        return roastedAndCooked;
    }
}
