package pl.kacperb333.java.foodbook.food.kitchen;

import org.testng.annotations.Test;
import pl.kacperb333.java.foodbook.food.kitchen.events.*;
import pl.kacperb333.java.foodbook.food.kitchen.values.EnergyValue;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class KitchenInvariantsTest {

    @Test
    void shouldAddLabeledShelfIfShelfDoesNotExists() {
        String newShelfLabel = "Vegetables";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(newShelfLabel));

        assertTrue(kitchen.shelfWithLabelExists(newShelfLabel));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenAddingShelfWithAlreadyExistingLabel() {
        String newShelfLabel = "Vegetables";
        LabeledShelfAddedEvent shelfAddedEvent = provideShelfAddedEvent(newShelfLabel);
        Kitchen kitchen = provideFreshKitchen();

        kitchen.addLabeledShelf(shelfAddedEvent);
        kitchen.addLabeledShelf(shelfAddedEvent);
    }

    @Test
    void shouldRemoveEmptyShelf() {
        String newShelfLabel = "Vegetables";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(newShelfLabel));
        kitchen.removeLabeledShelf(provideShelfRemovedEvent(newShelfLabel));

        assertFalse(kitchen.shelfWithLabelExists(newShelfLabel));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenRemovingNonEmptyShelf() {
        String ingredientName = "Potato";
        String newShelfLabel = "Vegetables";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(newShelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                newShelfLabel, 0));
        kitchen.removeLabeledShelf(provideShelfRemovedEvent(newShelfLabel));
    }

    @Test
    void shouldAddIngredientToShelfIfShelfExists() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(shelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, 0));

        assertTrue(kitchen.ingredientExists(ingredientName));
        assertFalse(kitchen.isShelfWithLabelEmpty(shelfLabel));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenAddingIngredientToNonExistingShelf() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, 0));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenAddingIngredientToDifferentShelfIfIngredientAlreadyExists() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";
        String differentshelfLabel = "Fruits";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(shelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, 0));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                differentshelfLabel, 0));
    }

    @Test
    void shouldAddIngredientSupplyIfIngredientExists() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";
        int supplyToAdd = 40;

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(shelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, 0));

        assertFalse(kitchen.canTakeSupply(ingredientName, supplyToAdd));
        kitchen.addIngredientSupply(provideIngredientSupplyAddedEvent(ingredientName, supplyToAdd));
        assertTrue(kitchen.canTakeSupply(ingredientName, supplyToAdd));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenAddingSupplyToNonExistingIngredient() {
        String ingredientName = "Potato";
        int supplyToAdd = 40;

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addIngredientSupply(provideIngredientSupplyAddedEvent(ingredientName, supplyToAdd));
    }

    @Test
    void shouldTakeSupplyIfEnoughIngredientSupplyIsPresent() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";
        int supplyToTake = 40;

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(shelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, 0));
        kitchen.addIngredientSupply(provideIngredientSupplyAddedEvent(ingredientName, supplyToTake));

        assertTrue(kitchen.canTakeSupply(ingredientName, supplyToTake));
        kitchen.takeIngredientSupply(provideIngredientSupplyTakenEvent(ingredientName, supplyToTake));
        assertFalse(kitchen.canTakeSupply(ingredientName, supplyToTake));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenTakingSupplyOfNonExistingIngredient() {
        String ingredientName = "Potato";
        int supplyToTake = 40;

        Kitchen kitchen = provideFreshKitchen();
        kitchen.takeIngredientSupply(provideIngredientSupplyTakenEvent(ingredientName, supplyToTake));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenTakingTooMuchSupplyOfAnIngredient() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";
        int presentSupply = 40;
        int supplyToTake = 50;

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(shelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, presentSupply));

        assertTrue(kitchen.canTakeSupply(ingredientName, presentSupply));
        assertFalse(kitchen.canTakeSupply(ingredientName, supplyToTake));
        kitchen.takeIngredientSupply(provideIngredientSupplyTakenEvent(ingredientName, supplyToTake));
    }

    @Test
    void shouldRemoveExistingIngredient() {
        String ingredientName = "Potato";
        String shelfLabel = "Vegetables";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.addLabeledShelf(provideShelfAddedEvent(shelfLabel));
        kitchen.addIngredient(provideIngredientAddedEvent(ingredientName, EnergyValue.fromKiloCalories(10),
                shelfLabel, 0));

        assertTrue(kitchen.ingredientExists(ingredientName));
        assertFalse(kitchen.isShelfWithLabelEmpty(shelfLabel));
        kitchen.removeIngredient(provideIngredientRemovedEvent(ingredientName));
        assertFalse(kitchen.ingredientExists(ingredientName));
        assertTrue(kitchen.isShelfWithLabelEmpty(shelfLabel));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenRemovingNonExistingIngredient() {
        String ingredientName = "Potato";

        Kitchen kitchen = provideFreshKitchen();
        kitchen.removeIngredient(provideIngredientRemovedEvent(ingredientName));
    }


    private Kitchen provideFreshKitchen() {
        return Kitchen.newKitchen();
    }

    private LabeledShelfAddedEvent provideShelfAddedEvent(String shelfLabel) {
        return new LabeledShelfAddedEvent(shelfLabel);
    }

    private LabeledShelfRemovedEvent provideShelfRemovedEvent(String shelfLabel) {
        return new LabeledShelfRemovedEvent(shelfLabel);
    }

    private IngredientAddedEvent provideIngredientAddedEvent(String ingredientName, EnergyValue energyValue,
                                                             String shelfLabel, int initialSupply) {
        return new IngredientAddedEvent(ingredientName, energyValue, shelfLabel, initialSupply);
    }

    private IngredientRemovedEvent provideIngredientRemovedEvent(String ingredientName) {
        return new IngredientRemovedEvent(ingredientName);
    }

    private IngredientSupplyAddedEvent provideIngredientSupplyAddedEvent(String ingredientName, int howMany) {
        return new IngredientSupplyAddedEvent(ingredientName, howMany);
    }

    private IngredientSupplyTakenEvent provideIngredientSupplyTakenEvent(String ingredientName, int howMany) {
        return new IngredientSupplyTakenEvent(ingredientName, howMany);
    }
}
