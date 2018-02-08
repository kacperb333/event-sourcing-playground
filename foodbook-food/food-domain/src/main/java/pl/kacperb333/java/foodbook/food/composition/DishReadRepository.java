package pl.kacperb333.java.foodbook.food.composition;

interface DishReadRepository {
    boolean existsByName(String name);
}
