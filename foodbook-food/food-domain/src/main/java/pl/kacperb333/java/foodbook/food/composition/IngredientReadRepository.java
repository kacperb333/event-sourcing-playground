package pl.kacperb333.java.foodbook.food.composition;

interface IngredientReadRepository {
    boolean existsByName(String name);
}
