package pl.kacperb333.java.foodbook.food.composition.ingredients;

interface IngredientReadRepository {
    boolean existsByName(String name);
}
