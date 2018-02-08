package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.food.composition.dto.CreateIngredientCategoryInput;
import pl.kacperb333.java.foodbook.food.composition.dto.IngredientCategoryAlreadyExistsException;

import static org.apache.commons.lang3.Validate.notNull;

final class IngredientCategoryFacade {

    private final IngredientCategoryWriteRepository writeRepository;
    private final IngredientCategoryReadRepository readRepository;

    public IngredientCategoryFacade(IngredientCategoryWriteRepository writeRepository,
                                    IngredientCategoryReadRepository readRepository) {
        this.writeRepository = writeRepository;
        this.readRepository = readRepository;
    }

    public IngredientCategory.Identifier createIngredientCategory(CreateIngredientCategoryInput input) {
        notNull(input);

        if (readRepository.existsByName(input.getName())) {
            throw new IngredientCategoryAlreadyExistsException(input.getName());
        }

        IngredientCategory created = writeRepository.save(IngredientCategory.newCategory(input.getName()));
        return created.getId();
    }
}
