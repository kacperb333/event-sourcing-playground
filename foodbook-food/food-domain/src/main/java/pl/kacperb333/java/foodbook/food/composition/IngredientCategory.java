package pl.kacperb333.java.foodbook.food.composition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@ToString
@Getter
class IngredientCategory {

    @RequiredArgsConstructor
    @Getter
    static class Identifier {
        private final Long id;
    }

    private final Identifier id;
    private final long version;
    private final String name;

    private IngredientCategory(Identifier id, long version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public static IngredientCategory newCategory(String name) {
        notEmpty(name);
        return new IngredientCategory(null, 0L , name);
    }

    public static IngredientCategory from(Identifier id, long version, String name) {
        notNull(id);
        notEmpty(name);
        return new IngredientCategory(id, version, name);
    }
}
