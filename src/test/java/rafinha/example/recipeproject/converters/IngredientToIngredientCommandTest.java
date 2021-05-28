package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.IngredientCommand;
import rafinha.example.recipeproject.domain.Ingredient;
import rafinha.example.recipeproject.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "Description";
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(1.0);
    public static final Long UOM_ID = Long.valueOf(1L);

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyParameter() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient ingredient = new Ingredient();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasureCommand());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasureCommand().getId());
    }

    @Test
    void convertWithNullUom() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUnitOfMeasure(null);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUnitOfMeasureCommand());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
    }
}