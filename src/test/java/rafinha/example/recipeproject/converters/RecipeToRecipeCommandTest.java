package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.domain.*;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        Category category1 = new Category();
        category1.setId(CATEGORY_ID_1);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        recipe.setId(ID_VALUE);
        recipe.setSource(SOURCE);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setDescription(DESC);
        recipe.setUrl(URL);
        recipe.setNotes(notes);
        recipe.setServings(SERVINGS);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        RecipeCommand recipeCommand = converter.convert(recipe);

        assertNotNull(recipeCommand);
        assertEquals(ID_VALUE, recipeCommand.getId());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DESC, recipeCommand.getDescription());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}