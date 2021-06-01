package rafinha.example.recipeproject.services;

import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipies();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand updateRecipeCommandById(Long id);

    void deleteRecipeById(Long id);
}
