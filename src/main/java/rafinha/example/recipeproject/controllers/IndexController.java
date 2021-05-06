package rafinha.example.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rafinha.example.recipeproject.domain.Category;
import rafinha.example.recipeproject.domain.UnitOfMeasure;
import rafinha.example.recipeproject.repositories.CategoryRepository;
import rafinha.example.recipeproject.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@RequestMapping
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName("Polish");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Tablespoon");
        if(categoryOptional.isPresent())
            System.out.println(categoryOptional.get().getId());
        if(unitOfMeasureOptional.isPresent())
            System.out.println(unitOfMeasureOptional.get().getId());
        return "index";
    }
}
