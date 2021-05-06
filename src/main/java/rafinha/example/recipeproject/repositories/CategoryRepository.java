package rafinha.example.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.recipeproject.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);
}
