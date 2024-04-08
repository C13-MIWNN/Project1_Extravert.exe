package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Nadine Beck
 * Handle all requests regarding recipes
 */
@Controller
public class RecipeController {

    @GetMapping("/")
    private String showRecipeOverview(Model model) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pasta Carbonara", "Cook it", "Pastadish"));

        model.addAttribute("allRecipes", recipes);
        return "recipeOverview";
    }
}
