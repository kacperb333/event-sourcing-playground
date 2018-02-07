package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.repository.DomainReadRepository;

interface IngredientReadRepository extends DomainReadRepository<BasicIngredient, BasicIngredient.Identifier> {
    boolean existsByName(String name);
}
