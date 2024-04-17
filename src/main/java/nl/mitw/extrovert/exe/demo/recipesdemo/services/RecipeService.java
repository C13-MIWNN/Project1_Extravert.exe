package nl.mitw.extrovert.exe.demo.recipesdemo.services;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeService {

    private SearchRepository searchRepository;


    @Autowired
    public RecipeService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }


    public List<Recipe> searchRecipes(String keyword) {
        if (keyword != null & !keyword.trim().isEmpty()) {
            return searchRepository.search(keyword);
        }
        return Collections.emptyList();
    }
}
