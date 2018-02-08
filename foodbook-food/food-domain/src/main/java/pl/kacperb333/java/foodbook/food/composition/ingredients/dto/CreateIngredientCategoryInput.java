package pl.kacperb333.java.foodbook.food.composition.ingredients.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
public class CreateIngredientCategoryInput {
    @NonNull
    private final String name;
}
