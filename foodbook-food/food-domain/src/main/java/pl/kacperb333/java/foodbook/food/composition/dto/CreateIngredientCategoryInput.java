package pl.kacperb333.java.foodbook.food.composition.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
public class CreateIngredientCategoryInput {
    @NonNull
    private final String name;
}
