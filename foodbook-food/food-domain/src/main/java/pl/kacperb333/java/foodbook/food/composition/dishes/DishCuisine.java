package pl.kacperb333.java.foodbook.food.composition.dishes;

import lombok.Value;

@Value
class DishCuisine {
    @Value
    static class Identifier {
        private final Long id;
    }

    private final Identifier id;
    private final long version;
    private final String name;
}
