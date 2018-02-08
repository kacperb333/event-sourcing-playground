package pl.kacperb333.java.foodbook.food.composition.dishes;

interface DishCuisineReadRepository {
    boolean existsByName(String name);
}
