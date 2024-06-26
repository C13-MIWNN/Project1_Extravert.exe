package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import jakarta.validation.Valid;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeIngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.services.newRecipeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    private final TagRepository tagRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final newRecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository,
                            IngredientRepository ingredientRepository,
                            TagRepository tagRepository,
                            RecipeIngredientRepository recipeIngredientRepository, newRecipeService recipeService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeService = recipeService;
    }

    @GetMapping({"/", "/recipe"})
    private String showRecipeOverview(Model model) {

        model.addAttribute("allIngredients", ingredientRepository.findAll(Sort.by("name")));
        model.addAttribute("allTags", tagRepository.findAll());
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("recipe/new")
    private String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return recipeFormSetup(model);
    }

    private String recipeFormSetup(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll(Sort.by("name")));
        model.addAttribute("allTags", tagRepository.findAll());
        model.addAttribute("allRecipeIngredientAmounts", recipeIngredientRepository.findAll());
        model.addAttribute(("RecipeIngredient"), new RecipeIngredient());

        return "recipeForm";
    }

    @PostMapping("recipe/new")
    private String showRecipeForm
            (@ModelAttribute("recipe") @Valid Recipe recipeToBeSaved, BindingResult recipeResult,
             @RequestParam(value = "selectedIngredients", required = false) List<Long> selectedIngredientIds,
             @RequestParam(value = "ingredientAmounts", required = false) List<Integer> ingredientAmounts,
             Model model) {

        if (selectedIngredientIds == null || selectedIngredientIds.isEmpty()) {
            model.addAttribute("ingredientError", true);
            return recipeFormSetup(model);
        }
        if (recipeResult.hasErrors()) {
            return recipeFormSetup(model);
        }

        recipeService.saveRecipe(recipeToBeSaved, selectedIngredientIds, ingredientAmounts);

        return "redirect:/";
    }



    @GetMapping("/recipe/{id}/edit")
    private String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            model.addAttribute("recipe", recipe);
            model.addAttribute("allIngredients",
                    ingredientRepository.findAll(Sort.by("name")));
            model.addAttribute("allTags", tagRepository.findAll());

            return "editRecipeForm";
        } else {

            return "redirect:/";
        }
    }

    @PostMapping("/recipe/{id}/edit")
    private String updateRecipe(
            @ModelAttribute("recipe") Recipe recipeToBeSaved,
            @PathVariable("id") Long id,
            BindingResult recipeResult) {

        if (!recipeResult.hasErrors()) {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
            if (optionalRecipe.isPresent()) {
                Recipe existingRecipe = optionalRecipe.get();

                existingRecipe.setName(recipeToBeSaved.getName());
                existingRecipe.setNumberOfServings(recipeToBeSaved.getNumberOfServings());
                existingRecipe.setPreparationTime(recipeToBeSaved.getPreparationTime());
                existingRecipe.setTags(recipeToBeSaved.getTags());
                existingRecipe.setRecipeSteps(recipeToBeSaved.getRecipeSteps());

                recipeRepository.save(existingRecipe);
            }
        }

        return "redirect:/";
    }

    @GetMapping("/recipe/{name}")
    private String showRecipeDetails(@PathVariable("name") String name, Model model) {
        Optional<Recipe> recipe = recipeRepository.findByName(name);

        if (recipe.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("recipeToBeShown", recipe.get());
        return "recipeDetail";
    }
}
