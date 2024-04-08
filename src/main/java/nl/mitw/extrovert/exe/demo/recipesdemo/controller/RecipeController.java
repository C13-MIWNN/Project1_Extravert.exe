package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Nadine Beck
 * Handle all requests regarding recipes
 */
@Controller
public class RecipeController {

    @GetMapping("/")
    private String showRecipeOverview() {
        return "recipeOverview";
    }
}
