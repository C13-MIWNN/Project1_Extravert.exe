package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Tag;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Handles all requests regarding tags
 *
 * @author B.J. Falkena
 */
@Controller
public class tagController {
    private final TagRepository tagRepository;
    private final IngredientRepository ingredientRepository;


    public tagController(TagRepository tagRepository, IngredientRepository ingredientRepository) {
        this.tagRepository = tagRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/searchByTag/{nameTag}")
    private String searchRecipeByTag(@PathVariable("nameTag") String nameTag, Model model) {
        Optional<Tag> tag = tagRepository.findByNameTag(nameTag);
        model.addAttribute("allIngredients",ingredientRepository.findAll());
        model.addAttribute("allTags",tagRepository.findAll());

        if(tag.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("tagsToBeShown", tag.get());
        return "tagSearch";
    }
}