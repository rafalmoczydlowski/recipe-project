package rafinha.example.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rafinha.example.recipeproject.commands.IngredientCommand;
import rafinha.example.recipeproject.converters.IngredientCommandToIngredient;
import rafinha.example.recipeproject.converters.IngredientToIngredientCommand;
import rafinha.example.recipeproject.domain.Ingredient;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.repositories.RecipeRepository;
import rafinha.example.recipeproject.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()) {
            // todo toss error if not found
            log.error("Recipe not found for id " + command.getRecipeId());
            return new IngredientCommand();
        }
        else {
            Recipe recipe = recipeOptional.get(); // if optional does have a value create domain object

            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream() // looking for ingredient value that already been assigned
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasureCommand().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            }
            else {
                // add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUnitOfMeasureCommand().getId()))
                        .findFirst();
            }
            // todo check for fail

            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }
}
