package pl.kacperb333.java.foodbook.food.kitchen;

import pl.kacperb333.java.foodbook.food.kitchen.events.*;

import java.util.HashMap;
import java.util.Map;

class Kitchen {
    private final Map<String, Shelf> shelves;
    private final Map<String, Ingredient> ingredients;

    private Kitchen(Map<String, Shelf> shelves, Map<String, Ingredient> ingredients) {
        this.shelves = shelves;
        this.ingredients = ingredients;
    }

    public static Kitchen newKitchen() {
        return new Kitchen(new HashMap<>(), new HashMap<>());
    }

    public void addLabeledShelf(LabeledShelfAddedEvent event) {
        String shelfLabel = event.getShelfLabel();
        if (shelfWithLabelExists(shelfLabel)) {
            throw new IllegalArgumentException(
                    String.format("Shelf labeled '%s' already exists", shelfLabel));
        }
        shelves.put(shelfLabel, Shelf.withLabel(shelfLabel));
    }

    public void removeLabeledShelf(LabeledShelfRemovedEvent event) {
        String shelfLabel = event.getShelfLabel();
        Shelf toRemove = getShelfLabeled(shelfLabel);
        if (!isShelfWithLabelEmpty(shelfLabel)) {
            throw new IllegalArgumentException(
                    String.format("Not empty shelf cannot be removed: %s", toRemove));
        }
        shelves.remove(shelfLabel);
    }

    public void addIngredient(IngredientAddedEvent event) {
        String ingredientName = event.getIngredientName();
        if (ingredientExists(ingredientName)) {
            throw new IllegalArgumentException(String.format("Ingredient named '%s' already exists", ingredientName));
        }
        Shelf shelf = getShelfLabeled(event.getShelfLabel());
        ingredients.put(ingredientName, Ingredient.from(
                ingredientName,
                event.getIngredientEnergyValue(),
                shelf,
                event.getInitialSupply()));
    }

    public void removeIngredient(IngredientRemovedEvent event) {
        Ingredient toRemove = getIngredientNamed(event.getIngredientName());
        ingredients.remove(toRemove.getName());
    }

    public void addIngredientSupply(IngredientSupplyAddedEvent event) {
        Ingredient toAddSupplyTo = getIngredientNamed(event.getIngredientName());
        toAddSupplyTo.addSupply(event.getHowMany());
    }

    public void takeIngredientSupply(IngredientSupplyTakenEvent event) {
        Ingredient toTakeSupplyFrom = getIngredientNamed(event.getIngredientName());
        toTakeSupplyFrom.removeSupply(event.getHowMany());
    }

    private Shelf getShelfLabeled(String shelfLabel) {
        if (!shelfWithLabelExists(shelfLabel)) {
            throw new IllegalArgumentException(
                    String.format("Shelf labeled '%s' does not exist. Shelves: %s", shelfLabel, shelves.keySet()));
        }
        return shelves.get(shelfLabel);
    }

    private Ingredient getIngredientNamed(String ingredientName) {
        if (!ingredientExists(ingredientName)) {
            throw new IllegalArgumentException(String.format("No ingredient named '%s' exists. Ingredients: %s",
                    ingredientName, ingredients.keySet()));
        }
        return ingredients.get(ingredientName);
    }

    public boolean shelfWithLabelExists(String shelfLabel) {
        return shelves.containsKey(shelfLabel);
    }

    public boolean isShelfWithLabelEmpty(String shelfLabel) {
        return ingredients.values().stream()
                .noneMatch(i -> i.isOnShelfLabeled(shelfLabel));
    }

    public boolean ingredientExists(String ingredientName) {
        return ingredients.containsKey(ingredientName);
    }

    public boolean canTakeSupply(String ingredientName, int howMany) {
        return ingredients.get(ingredientName).canTakeSupply(howMany);
    }
}
