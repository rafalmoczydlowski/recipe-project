package rafinha.example.recipeproject.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.converters.RecipeCommandToRecipe;
import rafinha.example.recipeproject.converters.RecipeToRecipeCommand;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.repositories.RecipeRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    RecipeService service;

    @Autowired
    RecipeRepository repository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Test
    @Transactional
    public void testSaveDescription() {
        Iterable<Recipe> recipes = repository.findAll();
        Recipe recipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(recipe);

        testRecipeCommand.setDescription("DESC");
        RecipeCommand savedRecipeCommand = service.saveRecipeCommand(testRecipeCommand);

        assertEquals("DESC", savedRecipeCommand.getDescription());
        assertEquals(recipe.getId(), savedRecipeCommand.getId());
        assertEquals(recipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(recipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
