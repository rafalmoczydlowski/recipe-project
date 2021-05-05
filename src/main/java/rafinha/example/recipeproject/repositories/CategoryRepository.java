package rafinha.example.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.recipeproject.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
