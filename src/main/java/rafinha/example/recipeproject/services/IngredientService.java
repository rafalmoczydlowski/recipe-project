package rafinha.example.recipeproject.services;

import rafinha.example.recipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findIngredientCommandByRecipeId(Long recipeId, Long ingredientId);
}