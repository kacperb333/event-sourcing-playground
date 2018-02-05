package pl.kacperb333.java.foodbook.food.composition;

class Preparers {

    public static Preparable cook(Preparable toCook, EnergyValue toAdd) {
        if (toCook.hasApplied(Cooker.class)) {
            throw new IllegalArgumentException("Overcooked");
        }
        return new Cooker(toCook, toAdd, "Cooked");
    }

    public static Preparable roast(Preparable toRoast, EnergyValue toAdd) {
        if (toRoast.hasApplied(Roaster.class)) {
            throw new IllegalArgumentException("Overroasted");
        }
        return new Roaster(toRoast, toAdd, "Roasted");
    }

    private static class Cooker extends IngredientPreparer {

        private Cooker(Preparable toPrepare, EnergyValue energyValueToAdd, String namePrefix) {
            super(toPrepare, energyValueToAdd, namePrefix);
        }
    }

    private static class Roaster extends IngredientPreparer {

        private Roaster(Preparable toPrepare, EnergyValue energyValueToAdd, String namePrefix) {
            super(toPrepare, energyValueToAdd, namePrefix);
        }
    }
}
