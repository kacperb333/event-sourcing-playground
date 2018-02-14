package pl.kacperb333.java.foodbook.food.kitchen.commands;

import lombok.Value;

@Value
public class RemoveLabeledShelfCommand {
    private final String shelfLabel;
}
