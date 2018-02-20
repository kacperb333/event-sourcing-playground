package pl.kacperb333.java.foodbook.food.inventory;

import pl.kacperb333.java.foodbook.eventsourcing.Repository;
import pl.kacperb333.java.foodbook.food.inventory.value.FoodCategoryIdentifier;

import java.util.Collection;

interface FoodCategoryRepository extends Repository<FoodCategory> {
    Collection<String> getAllActiveFoodCategoryNames();
    FoodCategoryIdentifier provideNewIdentifier();
}
