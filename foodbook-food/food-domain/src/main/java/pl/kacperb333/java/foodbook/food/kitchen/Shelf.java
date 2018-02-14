package pl.kacperb333.java.foodbook.food.kitchen;

import static org.apache.commons.lang3.Validate.notEmpty;

class Shelf {
    private final String label;

    private Shelf(String label) {
        this.label = label;
    }

    public static Shelf withLabel(String label) {
        notEmpty(label);
        return new Shelf(label);
    }

    public String getLabel() {
        return label;
    }
}
