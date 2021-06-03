package rafinha.example.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rafinha.example.recipeproject.commands.IngredientCommand;
import rafinha.example.recipeproject.converters.IngredientToIngredientCommand;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.repositories.RecipeRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findIngredientCommandByRecipeId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent())
            log.error("Recipe with an id equal to " + recipeId + " was not found!");

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream() // tworzymy strumień składników przepisu
                .filter(ingredient -> ingredient.getId().equals(ingredientId)) // filtrujemy strumień po numerze ID składników i wybieramy te, które są równe przesłanej wartości w metodzie
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst(); //konwertujemy znalezione obiekty typu Ingredient na IngredientCommand

        if(!ingredientCommandOptional.isPresent())
            log.error("Ingredient with an id equal to " + ingredientId + " was not found!");

        return ingredientCommandOptional.get();
    }
}
