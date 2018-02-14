package pl.kacperb333.java.foodbook.food.kitchen.commands;

import lombok.Value;

@Value
public class AddLabeledShelfCommand {
    private final String shelfLabel;
}
