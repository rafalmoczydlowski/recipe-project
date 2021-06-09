package rafinha.example.recipeproject.services;

import rafinha.example.recipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findIngredientCommandByRecipeId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteIngredientById(Long recipeId, Long ingredientId);
}
