package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.TagRepository;
import org.springframework.stereotype.Controller;

/**
 * hier de opdracht die je gaat maken
 *
 * @author B.J. Falkena
 */
@Controller
public class tagController {
    private final TagRepository tagRepository;


    public tagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
}