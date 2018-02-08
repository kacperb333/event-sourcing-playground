package pl.kacperb333.java.foodbook.food.composition.ingredients;

import lombok.Getter;
import lombok.ToString;
import pl.kacperb333.java.foodbook.food.composition.ingredients.values.IngredientCategoryId;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@ToString
@Getter
class IngredientCategory {

    private final IngredientCategoryId id;
    private final long version;
    private final String name;

    private IngredientCategory(IngredientCategoryId id, long version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public static IngredientCategory newCategory(String name) {
        notEmpty(name);
        return new IngredientCategory(null, 0L , name);
    }

    public static IngredientCategory from(IngredientCategoryId id, long version, String name) {
        notNull(id);
        notEmpty(name);
        return new IngredientCategory(id, version, name);
    }
}
