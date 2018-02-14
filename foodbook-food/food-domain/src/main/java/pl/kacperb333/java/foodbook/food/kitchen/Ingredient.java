package pl.kacperb333.java.foodbook.food.kitchen;

import pl.kacperb333.java.foodbook.food.kitchen.values.EnergyValue;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

class Ingredient {
    private final String name;
    private final EnergyValue energyValue;
    private Shelf shelf;
    private int supply;

    private Ingredient(String name, EnergyValue energyValue, Shelf shelf, int supply) {
        this.name = name;
        this.energyValue = energyValue;
        this.shelf = shelf;
        this.supply = supply;
    }

    public static Ingredient from(String name, EnergyValue energyValue, Shelf shelf, int supply) {
        notEmpty(name, "Ingredient must have a name");
        notNull(energyValue, "Ingredient must have a energy value");
        notNull(shelf, "Ingredient must be put on a shelf");
        isTrue(supply >= 0, "Ingredient cannot be of a negative supply: %d", supply);
        return new Ingredient(name, energyValue, shelf, supply);
    }

    public boolean isOnShelfLabeled(String shelfLabel) {
        return shelfLabel.equals(shelf.getLabel());
    }

    public void addSupply(int howMany) {
        if (howMany <= 0) {
            throw new IllegalArgumentException("Only positive supply can be added");
        }
        supply += howMany;
    }

    public void removeSupply(int howMany) {
        if (howMany <= 0) {
            throw new IllegalArgumentException("Only positive supply can be removed");
        }

        if (!canTakeSupply(howMany)) {
            throw new IllegalArgumentException(
                    String.format("Not enough supply to remove. To be removed: %s, actual supply: %s",
                            supply - howMany, supply));
        }

        supply -= howMany;
    }

    public boolean canTakeSupply(int howMany) {
        return supply - howMany >= 0;
    }

    public String getName() {
        return name;
    }

    public EnergyValue getEnergyValue() {
        return energyValue;
    }
}
