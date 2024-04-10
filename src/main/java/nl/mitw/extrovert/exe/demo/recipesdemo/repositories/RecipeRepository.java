package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
