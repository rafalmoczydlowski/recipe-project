package rafinha.example.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class IndexController {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        return "index";
    }
}
