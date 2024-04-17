package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByName(String name);
}
