package pl.kacperb333.java.foodbook.food.composition;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Set;

class BasicDishFactory {
    static BasicDish ofIngredients(Set<BasicIngredient> ingredients) {
        return BasicDish.create("Dish " + RandomStringUtils.random(10), ingredients);
    }
}
