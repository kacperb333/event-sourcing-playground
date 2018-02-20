package pl.kacperb333.java.foodbook.food.inventory;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kacperb333.java.foodbook.eventsourcing.EventStore;

@Configuration
class FoodInventoryConfiguration {

    @Bean
    FoodInventoryFacade facade(FoodCategoryRepository repository) {
        return new FoodInventoryFacade(repository);
    }

    @Bean
    FoodCategoryRepository foodCategoryRepository(EventStore eventStore) {
        return new InMemoryFoodCategoryRepository(eventStore, localActiveCategoryNamesReadModel());
    }

    @Bean
    EventStore eventStore(ApplicationEventPublisher eventPublisher) {
        return new InMemorySimpleEventStore(eventPublisher);
    }

    @Bean
    LocalActiveCategoryNamesReadModel localActiveCategoryNamesReadModel() {
        return new LocalActiveCategoryNamesReadModel();
    }
}
