package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Tag;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Unit;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Nadine Beck
 * Handles all requests regarding ingredients
 */
@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    private final List <Unit> units;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.units = Arrays.asList(Unit.values());
    }

    @GetMapping ("/ingredient")
    private String showAllIngredients(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll(Sort.by("name")));
        model.addAttribute("newIngredient",new Ingredient());
        model.addAttribute("unit",units);

        return "ingredientOverview";
    }

    @PostMapping("/ingredient/new")
    private String saveOrUpdateIngredient(@ModelAttribute("newIngredient") Ingredient ingredient,
                                          BindingResult result) {

        if (!result.hasErrors()){
            ingredientRepository.save(ingredient);
        }
        return "redirect:/ingredient";

    }

    @GetMapping ("ingredient/edit/{ingredientName}")
    private String showEditIngredientForm (@PathVariable("ingredientName") String ingredientName, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findByName(ingredientName);

        if (ingredient.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("ingredient", ingredient.get());
        model.addAttribute("unit",units);

        return "ingredientForm";
    }
}
