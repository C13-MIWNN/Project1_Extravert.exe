package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeIngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class recipeIngredientController {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public recipeIngredientController(RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }


    @RequestMapping(value = "/recipeIngredient/delete/{id}", method = RequestMethod.POST)
    public String deleteRecipeIngredient(@PathVariable Long id) {
        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findByRecipeIngredientId(id);

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();
            Long recipeId = recipeIngredient.getRecipe().getRecipeId();

            recipeIngredientRepository.delete(recipeIngredient);
            return "redirect:/recipe/" + recipeId + "/edit";
        }


        return "redirect:/error";
    }

    @RequestMapping(value = "/recipe/add/{id}", method = RequestMethod.POST)
    public String addRecipeIngredient(@PathVariable Long id,
                                      @ModelAttribute("ingredientId") Long ingredientId,
                                      @ModelAttribute("amount") Integer amount) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);

        if (optionalRecipe.isPresent() && optionalIngredient.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            Ingredient ingredient = optionalIngredient.get();

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(amount);

            recipeIngredientRepository.save(recipeIngredient);

        }
        return "redirect:/recipe/" + id + "/edit";

    }
}



