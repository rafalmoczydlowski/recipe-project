package rafinha.example.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.recipeproject.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
