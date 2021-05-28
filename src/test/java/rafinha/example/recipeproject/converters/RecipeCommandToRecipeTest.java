package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.CategoryCommand;
import rafinha.example.recipeproject.commands.IngredientCommand;
import rafinha.example.recipeproject.commands.NotesCommand;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.domain.Difficulty;
import rafinha.example.recipeproject.domain.Recipe;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final Integer SERVINGS = Integer.valueOf(8);
    public static final String URL = "URL";
    public static final String DESC = "Description";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.MEDIUM;
    public static final Integer PREP_TIME = Integer.valueOf(30);
    public static final Integer COOK_TIME = Integer.valueOf(50);
    public static final String SOURCE = "Source";

    public static final Long CATEGORY_ID_1 = 1L;
    public static final Long CATEGORY_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 1L;
    public static final Long INGREDIENT_ID_2 = 2L;
    public static final Long NOTES_ID = 1L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_2);
        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_2);

        recipeCommand.setId(ID_VALUE);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDescription(DESC);
        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);
        recipeCommand.setUrl(URL);
        recipeCommand.setNotes(notesCommand);
        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DESC, recipe.getDescription());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}