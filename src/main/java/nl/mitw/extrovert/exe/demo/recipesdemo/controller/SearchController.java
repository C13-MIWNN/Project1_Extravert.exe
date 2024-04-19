package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Nadine Beck
 * Omschrijving
 */
@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchRecipes (Model model, @RequestParam("keyword") String keyword) {
        List<Recipe> searchResults = searchService.searchRecipes(keyword);
        model.addAttribute("searchResults", searchResults);

        if (searchResults.isEmpty()) {
            return "redirect:/";
        }
        return "searchResults";
    }
}
