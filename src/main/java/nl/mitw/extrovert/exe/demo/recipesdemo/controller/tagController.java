package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Tag;
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


    public tagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping({"/searchByTag/{nameTag}"})
    private String searchRecipeByTag(@PathVariable("nameTag") String nameTag, Model model) {
        Optional<Tag> tag = tagRepository.findByNameTag(nameTag);
        model.addAttribute("allTags",tagRepository.findAll());


        if(tag.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("tagsToBeShown", tag.get());
        return "tagSearch";
    }
}