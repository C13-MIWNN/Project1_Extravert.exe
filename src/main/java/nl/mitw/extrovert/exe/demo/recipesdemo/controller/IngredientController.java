package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Nadine Beck
 * Omschrijving
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


}
