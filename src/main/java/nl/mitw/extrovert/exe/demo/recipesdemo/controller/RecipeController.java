package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Nadine Beck
 * Handle all requests regarding recipes
 */
@Controller
public class RecipeController {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;


    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"/","/recipe"})
    private String showRecipeOverview(Model model) {
        List<Recipe> recipes = new ArrayList<>();


        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }


    @GetMapping("recipe/new")
    private String showRecipeForm (Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allIngredients",ingredientRepository.findAll());

        return "recipeForm";
    }

    @PostMapping ("recipe/new")
    private String saveRecipe (@ModelAttribute("recipe") Recipe recipeToBeSaved, BindingResult result) {
        if (recipeToBeSaved.getRecipeId() == null
                && recipeRepository.findByName(recipeToBeSaved.getName()).isPresent()) {
            return "redirect:/recipe/new";
        }
        if (!result.hasErrors()){
            recipeRepository.save(recipeToBeSaved);
    }
        return "redirect:/";
    }

    @GetMapping ("recipe/edit/{recipeName}")
    private String showEditRecipeForm (@PathVariable("recipeName") String recipeName, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByName(recipeName);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipe", recipe.get());
        model.addAttribute("allIngredients",ingredientRepository.findAll());

        return "recipeForm";
    }

    @GetMapping("/recipe/{name}")
    private String showRecipeDetails(@PathVariable("name") String name, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByName(name);

        if(recipe.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipeToBeShown", recipe.get());
        return "recipeDetail";
    }

}
