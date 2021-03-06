package rafinha.example.recipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rafinha.example.recipeproject.commands.RecipeCommand;
import rafinha.example.recipeproject.services.CategoryService;
import rafinha.example.recipeproject.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE = "recipe";

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {
        log.debug("Getting Recipe By Id " + id);
        model.addAttribute(RECIPE, recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        log.debug("Create New Recipe");
        model.addAttribute(RECIPE, new RecipeCommand());
        model.addAttribute("categoryList", categoryService.listAllCategories());
        return "recipe/newrecipeform";
    }

    @PostMapping("recipe")
    public String saveAndUpdate(@ModelAttribute RecipeCommand command) {
        log.debug("Save/update recipe");
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipeById(@PathVariable String id, Model model) {
        log.debug("Update Recipe By Id " + id);
        model.addAttribute(RECIPE, recipeService.updateRecipeCommandById(Long.valueOf(id)));
        model.addAttribute("categoryList", categoryService.listAllCategories());
        return "recipe/newrecipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        log.debug("Deleting Recipe By Id " + id);
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }
}
