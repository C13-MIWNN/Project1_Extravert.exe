package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Nadine Beck
 * Handles all requests regarding ingredients
 */
@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping ("/ingredient")
    private String showAllIngredients(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("newIngredient",new Ingredient());

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

    @GetMapping("/search/{name}")
    private String showRecipeDetails(@PathVariable("name") String name, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findByName(name);
        model.addAttribute("allIngredients",ingredientRepository.findAll());

        if(ingredient.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("ingredientToBeShown", ingredient.get());
        return "ingredientSearch";
    }


}
