package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.repository.DomainReadRepository;

interface DishReadRepository extends DomainReadRepository<BasicDish, BasicDish.Identifier> {
    boolean existsByName(String name);
}
