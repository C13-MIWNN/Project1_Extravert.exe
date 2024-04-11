package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Unit;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jeroen van der Weide
 * <p>
 * Set some initial data in the database for testing purposes
 **/

@Controller
public class InitializeController {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;


    public InitializeController(IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }


    @GetMapping("/initialize")
    private String initializeDB() {

        Ingredient butter = makeIngredient("butter",Unit.GRAM);
        Ingredient cheese = makeIngredient("cheese",Unit.GRAM);
        Ingredient eggs = makeIngredient("eggs",Unit.GRAM);
        Ingredient milk = makeIngredient("milk",Unit.MILLILITERS);

        Recipe dutchVla = makeRecipe("dutch vla", milk, "Stir it",
                "90 min", "Breakfast");
        Recipe scrambledEggs = makeRecipe("Scrambled eggs", eggs, "Scramble it",
                "15 min", "Breakfast");
        Recipe grilledCheese = makeRecipe("Grilled Cheese Sandwich", cheese, "Toast it",
                "15 min","Lunch");
        Recipe milkShake = makeRecipe("Milkshake", milk, "Shake it",
                "15 min","Dessert");



        return "redirect:/";
    }


    private Ingredient makeIngredient(String name, Unit unit) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setUnit(unit);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    private Recipe makeRecipe(String name, Ingredient ingredient,
                              String preparation, String preparationTime, String tag) {
        Recipe recipe = new Recipe();
        recipe.setName(name);

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient);
        recipe.setIngredients(ingredientSet);

        recipe.setPreparation(preparation);
        recipe.setPreparationTime(preparationTime);
        recipe.setTag(tag);

        recipeRepository.save(recipe);
        return recipe;
    }

}
