package nl.mitw.extrovert.exe.demo.recipesdemo.controller;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Tag;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeIngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
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


    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, TagRepository tagRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @GetMapping({"/","/recipe"})
    private String showRecipeOverview(Model model) {



        model.addAttribute("allIngredients",ingredientRepository.findAll(Sort.by("name")));
        model.addAttribute("allTags", tagRepository.findAll());
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }


    @GetMapping("recipe/new")
    private String showRecipeForm (Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allIngredients",ingredientRepository.findAll(Sort.by("name")));
        model.addAttribute("allTags", tagRepository.findAll());
        model.addAttribute("allRecipeIngredientAmounts",recipeIngredientRepository.findAll());
        model.addAttribute(("RecipeIngredient"),new RecipeIngredient());

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveRecipe(
            @ModelAttribute("recipe") Recipe recipeToBeSaved,
            @RequestParam("selectedIngredients") List<Long> selectedIngredientIds,
            @RequestParam("ingredientAmounts") List<Integer> ingredientAmounts,
            BindingResult recipeResult) {

        if (!recipeResult.hasErrors()) {

        Recipe savedRecipe = recipeRepository.save(recipeToBeSaved);

        List<Ingredient> selectedIngredients = ingredientRepository.findAllById(selectedIngredientIds);
        for (int i = 0; i < selectedIngredients.size(); i++) {
            Ingredient ingredient = selectedIngredients.get(i);
            Integer amount = ingredientAmounts.get(i);

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(savedRecipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(amount);

                recipeIngredientRepository.save(recipeIngredient);
            }

        }

        return "redirect:/";
    }

    @GetMapping("/recipe/{id}/edit")
    private String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            // Fetch RecipeIngredients for the specific recipe
            List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findByRecipe(recipe);

            // Add recipe, ingredients, tags, and recipeIngredients to the model
            model.addAttribute("recipe", recipe);
            model.addAttribute("allIngredients", ingredientRepository.findAll(Sort.by("name")));
            model.addAttribute("allTags", tagRepository.findAll());
            model.addAttribute("recipeIngredients", recipeIngredients);

            return "editRecipeForm";
        } else {
            // Handle case where recipe with given id is not found
            return "redirect:/"; // Or any appropriate error handling
        }
    }

    @PostMapping("/recipe/{id}/edit")
    private String updateRecipe(
            @ModelAttribute("recipe") Recipe recipeToBeSaved,
            @RequestParam("selectedIngredients") List<Long> selectedIngredientIds,
            @RequestParam("ingredientAmounts") List<Integer> ingredientAmounts,
            @PathVariable("id") Long id,
            BindingResult recipeResult) {

        if (!recipeResult.hasErrors()) {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
            if (optionalRecipe.isPresent()) {
                Recipe existingRecipe = optionalRecipe.get();

                List<RecipeIngredient> existingRecipeIngredients = existingRecipe.getIngredients();
                List<Ingredient> selectedIngredients = ingredientRepository.findAllById(selectedIngredientIds);

                for (int i = 0; i < selectedIngredients.size(); i++) {
                    Ingredient ingredient = selectedIngredients.get(i);
                    Integer amount = ingredientAmounts.get(i);

                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setRecipe(existingRecipe);
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredient.setAmount(amount);

                    existingRecipeIngredients.add(recipeIngredient);
                }

                existingRecipe.setIngredients(existingRecipeIngredients);

                recipeRepository.save(existingRecipe);
            }
        }

        return "redirect:/";
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
