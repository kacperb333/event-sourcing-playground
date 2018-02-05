package pl.kacperb333.java.foodbook.food.composition;

import java.util.Optional;

interface IngredientRepository {
    BasicIngredient.Identifier provideNewIdentifier();
    Optional<BasicIngredient> find(BasicIngredient.Identifier id);
}
