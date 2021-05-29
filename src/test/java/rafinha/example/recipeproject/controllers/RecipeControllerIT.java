package rafinha.example.recipeproject.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rafinha.example.recipeproject.commands.NotesCommand;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.services.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecipeControllerIT {

    @Autowired
    RecipeController controller;

    @Autowired
    RecipeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeController(service);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }
    @Test
    void saveAndUpdate() {
        RecipeCommand recipeCommand = new RecipeCommand();
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(1L);
        recipeCommand.setId(1L);
        recipeCommand.setNotes(notesCommand);

        String viewName = controller.saveAndUpdate(recipeCommand);
        assertEquals("redirect:/recipe/show/1", viewName);
    }
}