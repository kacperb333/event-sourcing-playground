package pl.kacperb333.java.foodbook.food.kitchen;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;


class IngredientCompartment {
    private final Ingredient ingredient;
    private int supply;

    private IngredientCompartment(Ingredient ingredient, int supply) {
        this.ingredient = ingredient;
        this.supply = supply;
    }

    public static IngredientCompartment emptyCompartment(Ingredient ingredient) {
        return initializedCompartment(ingredient, 0);
    }

    public static IngredientCompartment initializedCompartment(Ingredient ingredient, int supply) {
        notNull(ingredient, "Compartment must be designated for an ingredient");
        isTrue(supply >= 0, "Filled compartment must contain a non-negative supply of ingredients");
        return new IngredientCompartment(ingredient, supply);
    }

    public void addIngredients(int toAdd) {
        isTrue(toAdd > 0, "Only positive supply of ingredients can be added");
        supply = supply + toAdd;
    }

    public void removeIngredients(int toRemove) {
        isTrue(toRemove > 0, "Only positive supply of ingredients can be removed");

        int supplyAfterRemoving = supply - toRemove;
        if (supplyAfterRemoving < 0) {
            throw new IllegalArgumentException(
                    String.format("Supply after removing ingredients cannot be less than zero. Left after removal: %s",
                            supplyAfterRemoving)
            );
        }

        supply = supplyAfterRemoving;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getSupply() {
        return supply;
    }
}
