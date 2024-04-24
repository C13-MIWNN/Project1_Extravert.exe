package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SearchRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT recipe FROM Recipe recipe"
            + " LEFT JOIN recipe.ingredients ingredient"
            + " LEFT JOIN recipe.tags tag"
            + " WHERE recipe.name LIKE %?1%"
            + " OR ingredient.ingredient.name LIKE %?1%"
            + " OR tag.nameTag LIKE %?1%")
    List<Recipe> search(String keyword);
}
