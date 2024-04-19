package nl.mitw.extrovert.exe.demo.recipesdemo;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.RecipeIngredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Nadine Beck
 * Omschrijving
 */

class RecipeIngredientNutritionTest {
    @Test
    @DisplayName("Fat for ingredient with no amount and 0 fat")
    void noFatForIngredientWithNoAmount() {
        Ingredient ingredient = new Ingredient();
        ingredient.setFat(0);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(1);

        double expectedFat = 0;

        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with no amount and 100 grams of fat")
    void hundredGramsFatForIngredientWithNoAmount() {
        Ingredient ingredient = new Ingredient();
        ingredient.setFat(100);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(0);

        double expectedFat = 0;

        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with 10g amount and 0 fat")
    void noFatForTenGramAmountIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setFat(0);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(10);

        double expectedFat = 0;

        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with 10 grams amount and 100 grams of fat")
    void hundredGramsFatForTenGramIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setUnit(Unit.GRAM);
        ingredient.setFat(100);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(10);

        double expectedFat = 10;

        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with 10 pieces amount and 100 grams of fat")
    void hundredGramsFatForTenPieceIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setUnit(Unit.PIECE);
        ingredient.setFat(100);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(10);

        double expectedFat = 1000;

        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }
}
