package rafinha.example.recipeproject.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rafinha.example.recipeproject.domain.*;
import rafinha.example.recipeproject.repositories.CategoryRepository;
import rafinha.example.recipeproject.repositories.RecipeRepository;
import rafinha.example.recipeproject.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipies());
        log.debug("Loading Bootstrap Data");
    }

    private List<Recipe> getRecipies() {

        List<Recipe> recipeList = new ArrayList<>(2);

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByUom("Tablespoon");
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByUom("Cup");
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByUom("Pinch");
        Optional<UnitOfMeasure> lbOptional = unitOfMeasureRepository.findByUom("Lb");
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByUom("Ounce");
        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByUom("Each");

        UnitOfMeasure teaspoon = callUnitOfMeasureIsPresentAndGetAccessToTheValue(teaspoonOptional);
        UnitOfMeasure tablespoon = callUnitOfMeasureIsPresentAndGetAccessToTheValue(tablespoonOptional);
        UnitOfMeasure cup = callUnitOfMeasureIsPresentAndGetAccessToTheValue(cupOptional);
        UnitOfMeasure pinch = callUnitOfMeasureIsPresentAndGetAccessToTheValue(pinchOptional);
        UnitOfMeasure lb = callUnitOfMeasureIsPresentAndGetAccessToTheValue(lbOptional);
        UnitOfMeasure ounce = callUnitOfMeasureIsPresentAndGetAccessToTheValue(ounceOptional);
        UnitOfMeasure each = callUnitOfMeasureIsPresentAndGetAccessToTheValue(eachOptional);

        Optional<Category> italianOptional = categoryRepository.findByCategoryName("Italian");
        Optional<Category> americanOptional = categoryRepository.findByCategoryName("American");

        Category italianCategory = callCategoryIsPresentAndGetAccessToTheValue(italianOptional);
        Category americanCategory = callCategoryIsPresentAndGetAccessToTheValue(americanOptional);

        Recipe lasagnaRecipe = new Recipe();
        lasagnaRecipe.setDescription("The Best Lasagna Recipe (Simple & Classic)");
        lasagnaRecipe.setPrepTime(15);
        lasagnaRecipe.setCookTime(90);
        lasagnaRecipe.setServings(8);
        lasagnaRecipe.setDifficulty(Difficulty.MEDIUM);
        lasagnaRecipe.setDirections("1. Put pasta water on to boil" +
                "\n" +
                "2. Brown the ground beef" +
                "\n" +
                "3. Cook the bell pepper, onions, garlic, add back the beef" +
                "\n" +
                "4. Transfer to medium sized pot, add tomatoes and remaining sauce ingredients to build the sauce" +
                "\n" +
                "5. Boil and drain the lasagna noodles" +
                "\n" +
                "6. Heat the oven to 375°F" +
                "\n" +
                "7. Assemble the lasagna" +
                "\n" +
                "8. Bake" +
                "\n" +
                "9. Cool and serve");
        lasagnaRecipe.setUrl("https://www.simplyrecipes.com/recipes/lasagna/");
        Notes lasagnaNotes = new Notes();
        lasagnaNotes.setRecipeNotes("This classic lasagna is made with an easy meat sauce as the base. Layer the sauce with noodles and cheese, then bake until bubbly! This is great for feeding a big family, and freezes well, too.");

        lasagnaRecipe.setNotes(lasagnaNotes);

        lasagnaRecipe.addIngredient(new Ingredient("Extra virgin olive oil", new BigDecimal(1), tablespoon));
        lasagnaRecipe.addIngredient(new Ingredient("lean ground beef", new BigDecimal(1), lb));
        lasagnaRecipe.addIngredient(new Ingredient("diced onion", new BigDecimal("0.75"), cup));
        lasagnaRecipe.addIngredient(new Ingredient("large diced bell pepper (green, red, or yellow)", new BigDecimal("0.75"), cup));
        lasagnaRecipe.addIngredient(new Ingredient("minced cloves garlic", new BigDecimal(2), each));
        lasagnaRecipe.addIngredient(new Ingredient("28-ounce can good quality tomato sauce", new BigDecimal(1), each));
        lasagnaRecipe.addIngredient(new Ingredient(" tomato paste", new BigDecimal(3), ounce));
        lasagnaRecipe.addIngredient(new Ingredient("14-ounce can crushed tomatoes", new BigDecimal(1), each));
        lasagnaRecipe.addIngredient(new Ingredient("chopped fresh oregano", new BigDecimal(2), tablespoon));
        lasagnaRecipe.addIngredient(new Ingredient("chopped fresh parsley (preferably flat leaf)", new BigDecimal("0.25"), cup));
        lasagnaRecipe.addIngredient(new Ingredient("Italian Seasoning", new BigDecimal(1), tablespoon));
        lasagnaRecipe.addIngredient(new Ingredient("garlic powder and/or garlic salt", new BigDecimal(1), pinch));
        lasagnaRecipe.addIngredient(new Ingredient("red or white wine vinegar", new BigDecimal(1), tablespoon));
        lasagnaRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), tablespoon));
        lasagnaRecipe.addIngredient(new Ingredient("salt", new BigDecimal(1), tablespoon));
        lasagnaRecipe.addIngredient(new Ingredient("dry lasagna noodles ", new BigDecimal("0.5"), lb));
        lasagnaRecipe.addIngredient(new Ingredient("Ricotta cheese", new BigDecimal(15), ounce));
        lasagnaRecipe.addIngredient(new Ingredient("Mozzarella cheese", new BigDecimal(24), ounce));
        lasagnaRecipe.addIngredient(new Ingredient("freshly grated Parmesan cheese", new BigDecimal(4), ounce));

        lasagnaRecipe.getCategories().add(italianCategory);
        lasagnaRecipe.getCategories().add(americanCategory);

        recipeList.add(lasagnaRecipe);

        Recipe applePieRecipe = new Recipe();
        applePieRecipe.setDescription("Homemade Apple Pie");
        applePieRecipe.setPrepTime(75);
        applePieRecipe.setCookTime(55);
        applePieRecipe.setServings(8);
        applePieRecipe.setDifficulty(Difficulty.EASY);
        applePieRecipe.setDirections("1. Peel, core, and slice the apples" +
                "\n" +
                "2. Make the apple pie filling" +
                "\n" +
                "3. Prepare oven" +
                "\n" +
                "4. Roll out the dough and line bottom pie plate" +
                "\n" +
                "5. Place apple slices on top of the bottom crust" +
                "\n" +
                "6. Roll out top crust, place over apples, trim and crimp edges" +
                "\n" +
                "7. Brush with egg wash, cut vents" +
                "\n" +
                "8. Bake" +
                "\n" +
                "9. Let cool");
        applePieRecipe.setUrl("https://www.simplyrecipes.com/recipes/old_fashioned_apple_pie/");

        Notes applePieNotes = new Notes();
        applePieNotes.setRecipeNotes("There's nothing better than a slice of warm apple pie with a scoop of vanilla ice cream, right? For something a little different, try cinnamon ice cream instead. You could also top it with homemade whipped cream or caramel sauce.");
        applePieRecipe.setNotes(applePieNotes);

        applePieRecipe.addIngredient(new Ingredient("apples ", new BigDecimal(3), lb));
        applePieRecipe.addIngredient(new Ingredient("lemon juice or apple cider vinegar", new BigDecimal(1), tablespoon));
        applePieRecipe.addIngredient(new Ingredient("sugar", new BigDecimal("0.5"), cup));
        applePieRecipe.addIngredient(new Ingredient("flour ", new BigDecimal(3), tablespoon));
        applePieRecipe.addIngredient(new Ingredient("cinnamon", new BigDecimal("0.5"), teaspoon));
        applePieRecipe.addIngredient(new Ingredient("ground allspice", new BigDecimal("0.25"), teaspoon));
        applePieRecipe.addIngredient(new Ingredient("ground nutmeg", new BigDecimal("0.25"), teaspoon));
        applePieRecipe.addIngredient(new Ingredient("brandy ", new BigDecimal(1), tablespoon));
        applePieRecipe.addIngredient(new Ingredient("vanilla extract", new BigDecimal(1), teaspoon));
        applePieRecipe.addIngredient(new Ingredient("large egg ", new BigDecimal(1), each));
        applePieRecipe.addIngredient(new Ingredient("cream", new BigDecimal(1), tablespoon));

        applePieRecipe.getCategories().add(americanCategory);

        recipeList.add(applePieRecipe);

        return recipeList;
    }

     private UnitOfMeasure callUnitOfMeasureIsPresentAndGetAccessToTheValue(Optional<UnitOfMeasure> unitOfMeasureOptional) {
        if(unitOfMeasureOptional.isPresent())
            return unitOfMeasureOptional.get();
        return null;
     }

    private Category callCategoryIsPresentAndGetAccessToTheValue(Optional<Category> categoryOptional) {
        if(categoryOptional.isPresent())
            return categoryOptional.get();
        return null;
    }
}
