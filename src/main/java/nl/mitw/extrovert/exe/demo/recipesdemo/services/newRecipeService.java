package nl.mitw.extrovert.exe.demo.recipesdemo.services;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.IngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeIngredientRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class newRecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public newRecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public void saveRecipe(Recipe recipeToBeSaved, List<Long> selectedIngredientIds, List<Integer> ingredientAmounts) {
        Recipe savedRecipe = recipeRepository.save(recipeToBeSaved);
        List<Ingredient> selectedIngredients = ingredientRepository.findAllById(selectedIngredientIds);
        for (int i = 0; i < selectedIngredients.size(); i++) {
            Ingredient ingredient = selectedIngredients.get(i);
            Integer amount = ingredientAmounts.get(i);

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(savedRecipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(amount);

            recipeIngredientRepository.save(recipeIngredient);
        }
    }
}