package rafinha.example.recipeproject.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    Model model;

    @Mock
    private RecipeService recipeService;

    Set<Recipe> recipeSet = new HashSet<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }


    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    private Set<Recipe> setUpRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);

        recipeSet.add(recipe1);
        recipeSet.add(recipe2);

        return recipeSet;
    }

    @Test
    void getIndexPage() {
        recipeSet = setUpRecipes();

        when(recipeService.getRecipies()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = indexController.getIndexPage(model);

        assertEquals("index", viewName);

        verify(recipeService, times(1)).getRecipies();
        verify(model, times(1)).addAttribute(eq("recipies"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}