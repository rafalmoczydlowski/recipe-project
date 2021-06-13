package rafinha.example.recipeproject.services;

import rafinha.example.recipeproject.commands.CategoryCommand;

import java.util.Set;

public interface CategoryService {
    Set<CategoryCommand> listAllCategories();
}
