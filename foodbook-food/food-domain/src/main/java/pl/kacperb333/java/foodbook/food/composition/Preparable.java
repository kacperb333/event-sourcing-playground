package pl.kacperb333.java.foodbook.food.composition;

import java.util.Set;

interface Preparable {
    EnergyValue getEnergyValue();
    String getName();
    Set<Class<? extends Preparable>> alreadyApplied();
    boolean hasApplied(Class<? extends IngredientPreparer> preparerType);
}
