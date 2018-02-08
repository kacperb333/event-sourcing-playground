package pl.kacperb333.java.foodbook.food.composition.dishes;

interface DishReadRepository {
    boolean existsByName(String name);
}
