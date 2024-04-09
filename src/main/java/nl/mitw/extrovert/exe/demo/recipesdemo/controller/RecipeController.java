package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.controller.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Nadine Beck
 * Handle all requests regarding recipes
 */
@Controller
public class RecipeController {
     private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    private String showRecipeOverview(Model model) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pasta Carbonara", "Cook it", "Pastadish"));

        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }

    @GetMapping("recipe/new")
    private String showRecipeForm (Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipeForm";
    }

    @PostMapping ("recipe/new")
        private String saveRecipe (@ModelAttribute("recipe") Recipe recipeToBeSaved, BindingResult result) {
            if (!result.hasErrors()){
                recipeRepository.save(recipeToBeSaved);
        }
            return "redirect:/";
    }
}
