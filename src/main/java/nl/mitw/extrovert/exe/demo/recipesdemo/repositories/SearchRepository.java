package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Nadine Beck
 * Omschrijving
 */
public interface SearchRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "select recipe FROM Recipe recipe"
            + " WHERE recipe.name LIKE %?1%")
    List<Recipe> search(String keyword);
}
