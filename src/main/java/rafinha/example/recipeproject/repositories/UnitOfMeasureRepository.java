package rafinha.example.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.recipeproject.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUom(String uom);
}
