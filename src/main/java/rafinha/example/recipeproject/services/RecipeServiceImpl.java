package rafinha.example.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipies() {
        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
