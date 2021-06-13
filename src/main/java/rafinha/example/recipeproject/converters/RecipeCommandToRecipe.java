package rafinha.example.recipeproject.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null)
            return null;

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setServings(recipeCommand.getServings());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setSource(recipeCommand.getSource());
        recipe.setImage(recipeCommand.getImage());

        if(recipeCommand.getCategories() != null && !recipeCommand.getCategories().isEmpty())
            recipeCommand.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));

        if(recipeCommand.getIngredients() != null && !recipeCommand.getIngredients().isEmpty())
            recipeCommand.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));

        return recipe;
    }
}
