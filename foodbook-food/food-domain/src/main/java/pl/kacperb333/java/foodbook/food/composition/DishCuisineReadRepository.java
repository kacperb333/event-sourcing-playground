package pl.kacperb333.java.foodbook.food.composition;

import pl.kacperb333.java.foodbook.domain.repository.DomainReadRepository;

interface DishCuisineReadRepository extends DomainReadRepository<DishCuisine> {
    boolean existsByName(String name);
}
