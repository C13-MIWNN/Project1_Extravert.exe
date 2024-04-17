package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.*;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeIngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.services.CulinaryCompanionUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import java.util.*;

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
    private final RecipeIngredientRepository recipeIngredientRepository;

    private final CulinaryCompanionUserService culinaryCompanionUserService;


    public InitializeController(IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository,
                                TagRepository tagRepository,
                                RecipeIngredientRepository recipeIngredientRepository,
                                CulinaryCompanionUserService culinaryCompanionUserService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.culinaryCompanionUserService = culinaryCompanionUserService;
    }
    @EventListener
    private void seed(ContextRefreshedEvent event) {
        if (culinaryCompanionUserService.isNotInitialised()) {
            initializeDB();
        }
    }

    @GetMapping("/initialize")
    private String initializeDB() {
        CulinaryCompanionUser jeroen = makeUser("Jeroen","Jeroen");
        CulinaryCompanionUser bart = makeUser("Bart", "Bart");
        CulinaryCompanionUser nadine = makeUser("Nadine","Nadine");


        Ingredient butter = makeIngredient("butter",Unit.gram);
        Ingredient cheese = makeIngredient("cheese",Unit.gram);
        Ingredient eggs = makeIngredient("eggs",Unit.piece);
        Ingredient milk = makeIngredient("milk",Unit.ml);


        Tag breakfast = makeTag("Breakfast");
        Tag lunch = makeTag("Lunch");
        Tag dessert = makeTag("Dessert");


        Recipe dutchVla = makeRecipe("dutch vla", milk, 400,
                "90 min", 2, dessert);
        Recipe scrambledEggs = makeRecipe("Scrambled eggs", eggs, 4,
                "15 min", 1, breakfast);
        Recipe grilledCheese = makeRecipe("Grilled Cheese Sandwich", cheese, 200,
                "15 min", 1, lunch);
        Recipe milkShake = makeRecipe("Milkshake", milk, 700,
                "15 min", 4, dessert);


        return "redirect:/";
    }


    private Ingredient makeIngredient(String name, Unit unit) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setUnit(unit);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    private Tag makeTag(String name) {
        Tag tag = new Tag();
        tag.setNameTag(name);
        tagRepository.save(tag);
        return tag;
    }

    private List<String> makeRecipeStep() {
        List<String> steps = new ArrayList<>();
        steps.add("*First you do this*");
        steps.add("*Secondly you do this*");
        steps.add("*Don't forget to do this*");
        steps.add("*And then you do this*");
        steps.add("*Finally you do this*");
        steps.add("*But also you do this*");
        steps.add("*Finish it by doing this*");

        return steps;
    }

    private Recipe makeRecipe(String name, Ingredient ingredient, int amount,
                              String preparationTime, int numberOfServings, Tag tag) {

        Recipe recipe = new Recipe();
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        List<RecipeIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(recipeIngredient);
        recipe.setIngredients(ingredientList);

        recipe.setName(name);
        recipe.setNumberOfServings(numberOfServings);
        recipe.setRecipeSteps(makeRecipeStep());
        recipe.setPreparationTime(preparationTime);

        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(tag);
        recipe.setTags(tagSet);

        recipeRepository.save(recipe);

        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(amount);

        recipeIngredientRepository.save(recipeIngredient);

        return recipe;
    }

    private CulinaryCompanionUser makeUser(String username, String password) {
        CulinaryCompanionUser user = new CulinaryCompanionUser();
        user.setUsername(username);
        user.setPassword(password);
        culinaryCompanionUserService.saveUser(user);
        return user;
    }
}
