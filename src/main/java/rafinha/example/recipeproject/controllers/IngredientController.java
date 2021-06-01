package rafinha.example.recipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rafinha.example.recipeproject.services.RecipeService;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService service;

    public IngredientController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String getListOfIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list recipe number " + recipeId);
        model.addAttribute("recipe", service.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }
}
