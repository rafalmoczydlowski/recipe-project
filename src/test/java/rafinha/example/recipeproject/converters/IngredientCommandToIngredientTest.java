package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.IngredientCommand;
import rafinha.example.recipeproject.commands.UnitOfMeasureCommand;
import rafinha.example.recipeproject.domain.Ingredient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "Description";
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(1.0);
    public static final Long UOM_ID = Long.valueOf(2L);

    IngredientCommandToIngredient ingredientConverter;
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter;

    @BeforeEach
    void setUp() {
        unitOfMeasureConverter = new UnitOfMeasureCommandToUnitOfMeasure();
        ingredientConverter = new IngredientCommandToIngredient(unitOfMeasureConverter);
    }

    @Test
    void testNullParameter() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    void testEmptyParameter() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommand);

        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void convertWithNullUom() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}