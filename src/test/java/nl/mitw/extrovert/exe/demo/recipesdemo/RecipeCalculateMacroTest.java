package nl.mitw.extrovert.exe.demo.recipesdemo;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Recipe;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing for calculating the amount of macro's per serving within a recipe
 *
 * @author B.J. Falkena
 */
public class RecipeCalculateMacroTest {

    @Test
    @DisplayName("Macros for recipes with no macros and minimal amount and servings")
    void MacrosForRecipesWithNoAmountAndMinimalIngredientsAndServings() {

        int fat = 0;
        int carbs = 0;
        int protein = 0;
        int amountOfIngredients = 1;
        int servings = 1;

        Recipe recipe = getRecipe(fat,carbs,protein,amountOfIngredients,servings);

        double fatPerServing = 0;
        double carbsPerServing = 0;
        double proteinPerServing = 0;

        double expectedFatPerServing = 0;
        double expectedCarbsPerServing = 0;
        double expectedProteinPerServing = 0;

        assertEquals(expectedFatPerServing, fatPerServing);
        assertEquals(expectedCarbsPerServing, carbsPerServing);
        assertEquals(expectedProteinPerServing, proteinPerServing);
        
    }

    @Test
    @DisplayName("Macros for recipes with only fat one serving and 1 ingredient")
    void MacrosForRecipesWithFat() {

        int fat = 5;
        int carbs = 0;
        int protein = 0;
        int amountOfIngredients = 1;
        int servings = 1;

        Recipe recipe = getRecipe(fat,carbs,protein,amountOfIngredients,servings);

        double fatPerServing = recipe.calculateRecipeMacro(Recipe.MacroType.FAT);
        double carbsPerServing = recipe.calculateRecipeMacro(Recipe.MacroType.CARBS);
        double proteinPerServing = recipe.calculateRecipeMacro(Recipe.MacroType.PROTEIN);

        double expectedFatPerServing = 5;
        double expectedCarbsPerServing = 0;
        double expectedProteinPerServing = 0;

        assertEquals(expectedFatPerServing, fatPerServing);
        assertEquals(expectedCarbsPerServing, carbsPerServing);
        assertEquals(expectedProteinPerServing, proteinPerServing);

    }

    @Test
    @DisplayName("Macros for recipes with all macro's multiple servings and multiple ingredients")
    void MacrosForRecipesWithMultipleMacrosServingsIngredients() {

        int fat = 5;
        int carbs = 10;
        int protein = 20;
        int amountOfIngredients = 6;
        int servings = 4;

        Recipe recipe = getRecipe(fat,carbs,protein,amountOfIngredients,servings);

        double fatPerServing = recipe.calculateRecipeMacro(Recipe.MacroType.FAT);
        double carbsPerServing = recipe.calculateRecipeMacro(Recipe.MacroType.CARBS);
        double proteinPerServing = recipe.calculateRecipeMacro(Recipe.MacroType.PROTEIN);

        double expectedFatPerServing = 8;
        double expectedCarbsPerServing = 15;
        double expectedProteinPerServing = 30;

        assertEquals(expectedFatPerServing, fatPerServing);
        assertEquals(expectedCarbsPerServing, carbsPerServing);
        assertEquals(expectedProteinPerServing, proteinPerServing);

    }

    private static Recipe getRecipe(int fat, int carbs, int protein, int amount, int servings) {
        Ingredient ingredient = new Ingredient();
        ingredient.setFat(fat);
        ingredient.setCarbohydrates(carbs);
        ingredient.setProtein(protein);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(amount);

        List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
        recipeIngredientList.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setNumberOfServings(servings);
        recipe.setIngredients(recipeIngredientList);
        return recipe;
    }
}