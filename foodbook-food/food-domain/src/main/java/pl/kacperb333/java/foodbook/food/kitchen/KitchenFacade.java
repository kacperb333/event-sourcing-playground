package pl.kacperb333.java.foodbook.food.kitchen;

import pl.kacperb333.java.foodbook.food.kitchen.commands.AddLabeledShelfCommand;
import pl.kacperb333.java.foodbook.food.kitchen.commands.RemoveLabeledShelfCommand;
import pl.kacperb333.java.foodbook.food.kitchen.events.LabeledShelfAddedEvent;
import pl.kacperb333.java.foodbook.food.kitchen.events.LabeledShelfRemovedEvent;

public class KitchenFacade {
    private final KitchenEventStore kitchenEventStore;

    KitchenFacade(KitchenEventStore kitchenEventStore) {
        this.kitchenEventStore = kitchenEventStore;
    }

    public void addLabeledShelf(AddLabeledShelfCommand command) {
        String shelfLabel = command.getShelfLabel();

        Kitchen kitchen = kitchenEventStore.currentKitchen();
        if (kitchen.shelfWithLabelExists(shelfLabel)) {
            throw new IllegalArgumentException(String.format("Shelf exists: %s", shelfLabel));
        }

        kitchenEventStore.push(new LabeledShelfAddedEvent(shelfLabel));
    }

    public void removeLabeledShelf(RemoveLabeledShelfCommand command) {
        String shelfLabel = command.getShelfLabel();

        Kitchen kitchen = kitchenEventStore.currentKitchen();
        if (!kitchen.shelfWithLabelExists(shelfLabel)) {
            throw new IllegalArgumentException(String.format("Shelf does not exist: %s", shelfLabel));
        }

        kitchenEventStore.push(new LabeledShelfRemovedEvent(shelfLabel));
    }
}
