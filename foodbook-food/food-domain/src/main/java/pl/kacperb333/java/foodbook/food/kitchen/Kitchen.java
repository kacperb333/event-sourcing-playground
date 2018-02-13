package pl.kacperb333.java.foodbook.food.kitchen;

import pl.kacperb333.java.foodbook.food.kitchen.values.EnergyValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.Validate.notNull;

public class Kitchen {
    private final Map<String, Shelf> shelves;
    private final Map<String, Ingredient> knownIngredients;

    private Kitchen(Map<String, Shelf> shelves, Map<String, Ingredient> knownIngredients) {
        this.shelves = shelves;
        this.knownIngredients = knownIngredients;
    }

    public static Kitchen emptyKitchen() {
        return withShelvesAndKnownIngredients(new HashMap<>(), new HashMap<>());
    }

    public static Kitchen withShelvesAndKnownIngredients(Map<String, Shelf> shelves,
                                                         Map<String, Ingredient> knownIngredients) {
        notNull(shelves, "Kitchen must contain room for shelves");
        notNull(knownIngredients, "Kitchen must contain space for collecting known ingredients");
        return new Kitchen(shelves, knownIngredients);
    }

    public void addLabeledShelf(String shelfLabel) {
        if (shelfWithLabelExists(shelfLabel)) {
            throw new IllegalArgumentException(
                    String.format("Shelf labeled '%s' already exists", shelfLabel));
        }
        shelves.put(shelfLabel, Shelf.emptyShelf(shelfLabel));
    }

    public void removeLabeledShelf(String shelfLabel) {
        Shelf toRemove = getShelfLabeled(shelfLabel);
        if (!toRemove.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Not empty shelf cannot be removed: %s", toRemove));
        }

        shelves.remove(shelfLabel);
    }

    public void addCompartmentToShelf(String shelfLabel, String ingredientName) {
        Ingredient ingredientOfCompartment = getKnownIngredient(ingredientName);
        validateIfCompartmentAlreadyExistsFor(ingredientOfCompartment);
        Shelf toAddCompartmentTo = getShelfLabeled(shelfLabel);

        toAddCompartmentTo.addCompartment(ingredientOfCompartment);
    }

    public void removeCompartmentFromShelf(String shelfLabel, String ingredientName) {
        Ingredient ingredientOfCompartment = getKnownIngredient(ingredientName);
        Shelf toRemoveCompartmentFrom = getShelfLabeled(shelfLabel);
        toRemoveCompartmentFrom.removeCompartment(ingredientOfCompartment);
    }

    public void addIngredientToShelf(String ingredientName, int howMany) {
        Ingredient ingredientToAdd = getKnownIngredient(ingredientName);
        Shelf shelfOfIngredient = getShelfForIngredient(ingredientToAdd);
        shelfOfIngredient.addIngredients(ingredientToAdd, howMany);
    }

    public void removeIngredientFromShelf(String ingredientName, int howMany) {
        Ingredient ingredientToRemove = getKnownIngredient(ingredientName);
        Shelf shelfOfIngredient = getShelfForIngredient(ingredientToRemove);
        shelfOfIngredient.removeIngredients(ingredientToRemove, howMany);
    }

    public void addKnownIngredient(String ingredientName, EnergyValue energyValue) {
        if (knownIngredientWithNameExists(ingredientName)) {
            throw new IllegalArgumentException(
                    String.format("Known ingredient with name '%s' already exists", ingredientName));
        }
        knownIngredients.put(ingredientName, Ingredient.ofNameAndEnergyValue(ingredientName, energyValue));
    }

    public void removeKnownIngredient(String ingredientName) {
        if (!knownIngredientWithNameExists(ingredientName)) {
            throw new IllegalArgumentException(
                    String.format("No known ingredient with name '%s' exists", ingredientName));
        }

        Optional<Shelf> shelfWithCompartment = getShelfOfCompartmentFor(knownIngredients.get(ingredientName));
        if (shelfWithCompartment.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Know ingredient with existing compartment cannot be removed: %s",
                            shelfWithCompartment.get()));
        }

        knownIngredients.remove(ingredientName);
    }

    private void validateIfCompartmentAlreadyExistsFor(Ingredient ingredientOfCompartment) {
        Optional<Shelf> shelfWithCompartment = getShelfOfCompartmentFor(ingredientOfCompartment);
        if (shelfWithCompartment.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Compartment for %s already exists on shelf: %s",
                            ingredientOfCompartment, shelfWithCompartment.get()));
        }
    }

    private Optional<Shelf> getShelfOfCompartmentFor(Ingredient ingredientOfCompartment) {
        return shelves.values().stream()
                .filter(c -> c.containsCompartmentFor(ingredientOfCompartment))
                .findFirst();
    }

    private Shelf getShelfForIngredient(Ingredient ingredient) {
        Optional<Shelf> shelfForIngredient = getShelfOfCompartmentFor(ingredient);
        if (!shelfForIngredient.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("No compartment exists for %s", ingredient));
        }
        return shelfForIngredient.get();
    }

    private Shelf getShelfLabeled(String shelfLabel) {
        Shelf shelf = shelves.get(shelfLabel);
        if (shelf == null) {
            throw new IllegalArgumentException(
                    String.format("Shelf labeled '%s' does not exist", shelfLabel));
        }
        return shelf;
    }

    private Ingredient getKnownIngredient(String ingredientName) {
        Ingredient existingIngredient = knownIngredients.get(ingredientName);
        if (existingIngredient == null) {
            throw new IllegalArgumentException(
                    String.format("No known ingredient with name '%s' exists", ingredientName));
        }
        return existingIngredient;
    }


    private boolean shelfWithLabelExists(String shelfLabel) {
        return shelves.containsKey(shelfLabel);
    }

    private boolean knownIngredientWithNameExists(String ingredientName) {
        return knownIngredients.containsKey(ingredientName);
    }
}
