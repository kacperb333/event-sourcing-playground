package pl.kacperb333.java.foodbook.food.composition;

import java.util.Set;

/**
 *  Yep, it's a decorator
 */
abstract class IngredientPreparer implements Preparable {
    private final Preparable toPrepare;
    private final EnergyValue energyValueToAdd;
    private final String namePrefix;

    public IngredientPreparer(Preparable toPrepare, EnergyValue energyValueToAdd, String namePrefix) {
        this.toPrepare = toPrepare;
        this.energyValueToAdd = energyValueToAdd;
        this.namePrefix = namePrefix;
    }

    @Override
    public String getName() {
        return namePrefix + " " + toPrepare.getName();
    }

    @Override
    public EnergyValue getEnergyValue() {
        return toPrepare.getEnergyValue().plus(energyValueToAdd);
    }

    @Override
    public Set<Class<? extends Preparable>> alreadyApplied() {
        Set<Class<? extends Preparable>> alreadyApplied = toPrepare.alreadyApplied();
        alreadyApplied.add(this.getClass());
        return alreadyApplied;
    }

    @Override
    public boolean hasApplied(Class<? extends IngredientPreparer> preparerType) {
        return alreadyApplied().contains(preparerType);
    }
}
