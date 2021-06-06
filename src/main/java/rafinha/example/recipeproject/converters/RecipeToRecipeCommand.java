package rafinha.example.recipeproject.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null)
            return null;

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setSource(recipe.getSource());

        if(recipe.getCategories() != null && !recipe.getCategories().isEmpty())
            recipe.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));

        if(recipe.getIngredients() != null && !recipe.getCategories().isEmpty())
            recipe.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));

        return recipeCommand;
    }
}
