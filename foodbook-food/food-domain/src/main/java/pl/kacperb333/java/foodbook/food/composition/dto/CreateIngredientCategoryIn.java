package pl.kacperb333.java.foodbook.food.composition.dto;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "tryCreate")
public class CreateIngredientCategoryIn {
    @NonNull
    private final String name;
}
