package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.commonexception.EntityAlreadyExistsException;
import pl.kacperb333.java.foodbook.food.composition.dto.CreateIngredientCategoryIn;
import pl.kacperb333.java.foodbook.food.composition.dto.IngredientCategoryAlreadyExistsException;

import static org.apache.commons.lang3.Validate.notNull;

final class IngredientCategoryWriteFacade {

    private final IngredientCategoryWriteRepository writeRepository;
    private final IngredientCategoryReadRepository readRepository;

    public IngredientCategoryWriteFacade(IngredientCategoryWriteRepository writeRepository,
                                         IngredientCategoryReadRepository readRepository) {
        this.writeRepository = writeRepository;
        this.readRepository = readRepository;
    }

    public IngredientCategory.Identifier createIngredientCategory(CreateIngredientCategoryIn input) {
        notNull(input);

        try {
            IngredientCategory newCategory = IngredientCategory.newCategory(input.getName());
            IngredientCategory persisted = writeRepository.tryCreate(newCategory);
            return persisted.getId();
        } catch (EntityAlreadyExistsException e) {
            throw new IngredientCategoryAlreadyExistsException(input.getName());
        }
    }
}
