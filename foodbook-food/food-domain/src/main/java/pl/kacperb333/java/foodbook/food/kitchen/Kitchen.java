package pl.kacperb333.java.foodbook.food.kitchen;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notNull;

public class Kitchen {
    private final Set<Shelf> shelves;

    private Kitchen(Set<Shelf> shelves) {
        this.shelves = shelves;
    }

    public static Kitchen emptyKitchen() {
        return withShelves(new HashSet<>());
    }

    public static Kitchen withShelves(Set<Shelf> shelves) {
        notNull(shelves, "Kitchen must contain room for shelves");
        return new Kitchen(shelves);
    }
}
