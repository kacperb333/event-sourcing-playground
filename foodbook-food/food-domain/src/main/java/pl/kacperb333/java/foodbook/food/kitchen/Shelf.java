package pl.kacperb333.java.foodbook.food.kitchen;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

class Shelf {
    private final String label;
    private final Map<Ingredient, IngredientCompartment> ingredientCompartments;

    private Shelf(String label, Map<Ingredient, IngredientCompartment> ingredientCompartments) {
        this.label = label;
        this.ingredientCompartments = ingredientCompartments;
    }

    public static Shelf emptyShelf(String label) {
        return withCompartments(label, new HashMap<>());
    }

    public static Shelf withCompartments(String label, Map<Ingredient, IngredientCompartment> ingredientCompartments) {
        notEmpty(label, "Shelf must be labeled");
        notNull(ingredientCompartments, "Shelf must containt compartment space");
        return new Shelf(label, ingredientCompartments);
    }

    public void addCompartment(Ingredient ingredient) {
        if (ingredientCompartments.containsKey(ingredient)) {
            throw new IllegalArgumentException(
                    String.format("Compartment for ingredient already present on shelf: %s", ingredient));
        }

        ingredientCompartments.put(ingredient, IngredientCompartment.emptyCompartment(ingredient));
    }

    public void removeCompartment(Ingredient ingredient) {
        IngredientCompartment compartmentToRemove = getCompartmentForIngredient(ingredient);
        if (!compartmentToRemove.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Not empty compartment cannot be removed: %s", compartmentToRemove));
        }

        ingredientCompartments.remove(ingredient);
    }

    public void addIngredients(Ingredient ingredientToAdd, int howMany) {
        IngredientCompartment compartment = getCompartmentForIngredient(ingredientToAdd);
        compartment.addIngredients(howMany);
    }

    public void removeIngredients(Ingredient ingredientToRemove, int howMany) {
        IngredientCompartment compartment = getCompartmentForIngredient(ingredientToRemove);
        compartment.removeIngredients(howMany);
    }

    private IngredientCompartment getCompartmentForIngredient(Ingredient ingredient) {
        IngredientCompartment compartment = ingredientCompartments.get(ingredient);
        if (compartment == null) {
            throwCompartmentNotPresent(ingredient);
        }
        return compartment;
    }

    private void throwCompartmentNotPresent(Ingredient ingredient) {
        throw new IllegalArgumentException(
                String.format("Compartment for ingredient %s not present on the shelf", ingredient));
    }

    public String getLabel() {
        return label;
    }

    public boolean containsCompartmentFor(Ingredient ingredient) {
        return ingredientCompartments.containsKey(ingredient);
    }

    public boolean isEmpty() {
        return ingredientCompartments.isEmpty();
    }
}
