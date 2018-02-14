package pl.kacperb333.java.foodbook.food.kitchen.events;

import lombok.Value;

@Value
public class LabeledShelfRemovedEvent {
    private final String shelfLabel;
}
