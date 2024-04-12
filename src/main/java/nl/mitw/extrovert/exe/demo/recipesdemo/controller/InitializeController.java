package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Tag;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Unit;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.Optional;
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
    private final TagRepository tagRepository;


    public InitializeController(IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository,
                                TagRepository tagRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
    }


    @GetMapping("/initialize")
    private String initializeDB() {

        Ingredient butter = makeIngredient("butter",Unit.GRAM);
        Ingredient cheese = makeIngredient("cheese",Unit.GRAM);
        Ingredient eggs = makeIngredient("eggs",Unit.GRAM);
        Ingredient milk = makeIngredient("milk",Unit.MILLILITERS);


        Tag breakfast = makeTag("Breakfast");
        Tag lunch = makeTag("Lunch");
        Tag dessert = makeTag("Dessert");



        Recipe dutchVla = makeRecipe("dutch vla", milk, "Stir it",
                "90 min", dessert);
        Recipe scrambledEggs = makeRecipe("Scrambled eggs", eggs, "Scramble it",
                "15 min", breakfast);
        Recipe grilledCheese = makeRecipe("Grilled Cheese Sandwich", cheese, "Toast it",
                "15 min",lunch);
        Recipe milkShake = makeRecipe("Milkshake", milk, "Shake it",
                "15 min",dessert);


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
                              String preparation, String preparationTime, Tag tag) {

        Recipe recipe = new Recipe();
        recipe.setName(name);

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient);
        recipe.setIngredients(ingredientSet);

        recipe.setPreparation(preparation);
        recipe.setPreparationTime(preparationTime);

        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(tag);
        recipe.setTags(tagSet);

        recipeRepository.save(recipe);
        return recipe;
    }

    private Tag makeTag(String name) {
        Tag tag = new Tag();
        tag.setNameTag(name);
        tagRepository.save(tag);
        return tag;
    }
}
