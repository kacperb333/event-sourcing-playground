package pl.kacperb333.java.foodbook.food.composition.ingredients;

import pl.kacperb333.java.foodbook.food.composition.ingredients.dto.CreateIngredientCategoryInput;
import pl.kacperb333.java.foodbook.food.composition.ingredients.dto.IngredientCategoryAlreadyExistsException;
import pl.kacperb333.java.foodbook.food.composition.ingredients.values.IngredientCategoryId;

import static org.apache.commons.lang3.Validate.notNull;

final class IngredientCategoryFacade {

    private final IngredientCategoryWriteRepository writeRepository;
    private final IngredientCategoryReadRepository readRepository;

    public IngredientCategoryFacade(IngredientCategoryWriteRepository writeRepository,
                                    IngredientCategoryReadRepository readRepository) {
        this.writeRepository = writeRepository;
        this.readRepository = readRepository;
    }

    public IngredientCategoryId createIngredientCategory(CreateIngredientCategoryInput input) {
        notNull(input);

        if (readRepository.existsByName(input.getName())) {
            throw new IngredientCategoryAlreadyExistsException(input.getName());
        }

        IngredientCategory created = writeRepository.save(IngredientCategory.newCategory(input.getName()));
        return created.getId();
    }
}
