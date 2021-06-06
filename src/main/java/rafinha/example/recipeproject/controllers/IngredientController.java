package rafinha.example.recipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rafinha.example.recipeproject.commands.IngredientCommand;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.commands.UnitOfMeasureCommand;
import rafinha.example.recipeproject.services.IngredientService;
import rafinha.example.recipeproject.services.RecipeService;
import rafinha.example.recipeproject.services.UnitOfMeasureService;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService service;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    private static final String INGREDIENT = "ingredient";

    public IngredientController(RecipeService service, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.service = service;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String getListOfIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list recipe number " + recipeId);
        model.addAttribute("recipe", service.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId,
                                 @PathVariable String id, Model model) {
        log.debug("Show ingredient " + id + " of " + recipeId + "recipe");
        model.addAttribute(INGREDIENT, ingredientService.findIngredientCommandByRecipeId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String showNewIngredientForm(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = service.findCommandById(Long.valueOf(recipeId));
        // todo raise exception if recipeCommand is null

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute(INGREDIENT, ingredientCommand);
        ingredientCommand.setUnitOfMeasureCommand(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute(INGREDIENT, ingredientService.findIngredientCommandByRecipeId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        log.debug("Saved recipe id" + savedCommand.getRecipeId());
        log.debug("Saved ingredient id " + savedCommand.getId());
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
