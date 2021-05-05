package rafinha.example.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.recipeproject.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
