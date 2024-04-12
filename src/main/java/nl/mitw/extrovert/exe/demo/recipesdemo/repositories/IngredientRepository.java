package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IngredientRepository extends JpaRepository <Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
}
