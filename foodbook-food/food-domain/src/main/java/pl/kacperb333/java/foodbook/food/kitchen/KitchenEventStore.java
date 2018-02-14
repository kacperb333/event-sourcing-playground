package pl.kacperb333.java.foodbook.food.kitchen;

import pl.kacperb333.java.foodbook.food.kitchen.events.*;

interface KitchenEventStore {
    void push(IngredientAddedEvent ingredientAddedEvent);
    void push(IngredientRemovedEvent ingredientRemovedEvent);
    void push(IngredientSupplyAddedEvent ingredientSupplyAddedEvent);
    void push(IngredientSupplyTakenEvent ingredientSupplyTakenEvent);
    void push(LabeledShelfAddedEvent labeledShelfAddedEvent);
    void push(LabeledShelfRemovedEvent labeledShelfRemovedEvent);

    Kitchen currentKitchen();
}
