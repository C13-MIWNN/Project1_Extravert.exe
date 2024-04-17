package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import jakarta.transaction.Transactional;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * hier de opdracht die je gaat maken
 *
 * @author B.J. Falkena
 */
public interface RecipeIngredientRepository extends JpaRepository <RecipeIngredient, Long> {
    List<RecipeIngredient> findByRecipe(Recipe recipe);
}
