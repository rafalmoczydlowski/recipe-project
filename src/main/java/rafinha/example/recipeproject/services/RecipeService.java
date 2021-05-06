package rafinha.example.recipeproject.services;

import rafinha.example.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipies();
}
