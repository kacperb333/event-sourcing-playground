package pl.kacperb333.java.foodbook.food.kitchen.events;

import lombok.Value;

@Value
public class LabeledShelfAddedEvent {
    private final String shelfLabel;
}
