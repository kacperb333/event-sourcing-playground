package pl.kacperb333.java.foodbook.food.inventory;

import org.springframework.context.event.EventListener;
import pl.kacperb333.java.foodbook.eventsourcing.Identifier;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class LocalActiveCategoryNamesReadModel {
    private final Map<Identifier, String> existingCategoryNames = new HashMap<>();


    @EventListener
    public void addCategoryName(FoodCategoryCreatedEvent event) {
        existingCategoryNames.put(event.getAggregateIdentifier(), event.getCategoryName());
    }

    @EventListener
    public void removeCategoryName(FoodCategoryRemovedEvent event) {
        existingCategoryNames.remove(event.getAggregateIdentifier());
    }

    Map<Identifier, String> getExistingCategoryNames() {
        return Collections.unmodifiableMap(existingCategoryNames);
    }
}
