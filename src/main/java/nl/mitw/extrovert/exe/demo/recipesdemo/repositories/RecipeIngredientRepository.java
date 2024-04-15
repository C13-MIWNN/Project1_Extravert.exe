package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * hier de opdracht die je gaat maken
 *
 * @author B.J. Falkena
 */
public interface RecipeIngredientRepository extends JpaRepository <RecipeIngredient, Long> {
}
