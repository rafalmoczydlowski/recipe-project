package rafinha.example.recipeproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() throws Exception{
        Long id = 4L;

        category.setId(id);

        assertEquals(id, category.getId());
    }

    @Test
    void getCategoryName() {
        String name = "Kategoria";

        category.setCategoryName(name);

        assertEquals(name, category.getCategoryName());
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipes.add(recipe1);
        recipes.add(recipe2);

        category.setRecipes(recipes);

        assertEquals(recipes, category.getRecipes());
    }
}