package rafinha.example.recipeproject.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.services.RecipeService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService service;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IngredientController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void getListOfIngredientsTest() throws Exception {
        RecipeCommand command = new RecipeCommand();
        when(service.findCommandById(anyLong())).thenReturn(command);

         mockMvc.perform(get("/recipe/1/ingredients"))
                 .andExpect(status().isOk())
                 .andExpect(view().name("recipe/ingredient/list"))
                 .andExpect(model().attributeExists("recipe"));

         verify(service, times(1)).findCommandById(anyLong());
    }
}