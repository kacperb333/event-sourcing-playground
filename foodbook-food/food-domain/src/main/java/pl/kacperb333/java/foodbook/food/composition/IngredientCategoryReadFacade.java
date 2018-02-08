package pl.kacperb333.java.foodbook.food.composition;

final class IngredientCategoryReadFacade {

    private final IngredientCategoryReadRepository readRepository;

    public IngredientCategoryReadFacade(IngredientCategoryReadRepository readRepository) {
        this.readRepository = readRepository;
    }
}
