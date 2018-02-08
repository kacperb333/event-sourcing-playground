package pl.kacperb333.java.foodbook.food.composition;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kacperb333.java.foodbook.food.composition.dto.CreateIngredientCategoryIn;

import java.util.Optional;
import java.util.concurrent.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class IngredientCategoryWriteTest {

    private TestIngredientCategoryReadRepository readRepository;
    private TestIngredientCategoryWriteRepository writeRepository;

    private IngredientCategoryWriteFacade facade;

    @BeforeMethod
    void beforeMethod() {
        ConcurrentMap<IngredientCategory.Identifier, IngredientCategory> database = new ConcurrentHashMap<>();

        readRepository = new TestIngredientCategoryReadRepository(database);
        writeRepository = new TestIngredientCategoryWriteRepository(database);

        facade = new IngredientCategoryWriteFacade(writeRepository, readRepository);
    }

    @Test
    void savedCategoryShouldBeObtainable() {
        String newCategoryName = "New Category";
        CreateIngredientCategoryIn createInput = CreateIngredientCategoryIn.tryCreate(newCategoryName);

        IngredientCategory.Identifier createdId = facade.createIngredientCategory(createInput);

        Optional<IngredientCategory> obtained = readRepository.find(createdId);

        assertTrue(obtained.isPresent());
        assertEquals(obtained.get().getId(), createdId);
        assertEquals(obtained.get().getName(), newCategoryName);
    }
}
