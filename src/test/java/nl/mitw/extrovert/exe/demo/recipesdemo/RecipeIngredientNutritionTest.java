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

    private Ingredient setupIngredient(int fat, Unit unit) {
        Ingredient ingredient = new Ingredient();
        ingredient.setFat(fat);
        ingredient.setUnit(unit);
        return ingredient;
    }

    private RecipeIngredient setupRecipeIngredient(Ingredient ingredient, int amount) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(amount);
        return recipeIngredient;
    }
    @Test
    @DisplayName("Fat for ingredient with no amount and 0 fat")
    void noFatForIngredientWithNoAmount() {
        Ingredient ingredient = setupIngredient(0, Unit.GRAM);

        RecipeIngredient recipeIngredient = setupRecipeIngredient(ingredient, 1);

        double expectedFat = 0;
        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with no amount and 100 grams of fat")
    void hundredGramsFatForIngredientWithNoAmount() {
        Ingredient ingredient = setupIngredient(100, Unit.GRAM);

        RecipeIngredient recipeIngredient = setupRecipeIngredient(ingredient, 0);

        double expectedFat = 0;
        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with 10g amount and 0 fat")
    void noFatForTenGramAmountIngredient() {
        Ingredient ingredient = setupIngredient(0, Unit.GRAM);

        RecipeIngredient recipeIngredient = setupRecipeIngredient(ingredient, 10);

        double expectedFat = 0;
        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with 10 grams amount and 100 grams of fat")
    void hundredGramsFatForTenGramIngredient() {
        Ingredient ingredient = setupIngredient(100, Unit.GRAM);

        RecipeIngredient recipeIngredient = setupRecipeIngredient(ingredient, 10);

        double expectedFat = 10;
        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

    @Test
    @DisplayName("Fat for ingredient with 10 pieces amount and 100 grams of fat")
    void hundredGramsFatForTenPieceIngredient() {
        Ingredient ingredient = setupIngredient(100, Unit.PIECE);

        RecipeIngredient recipeIngredient = setupRecipeIngredient(ingredient, 10);

        double expectedFat = 1000;
        double fat = recipeIngredient.calculateFatForAmount();

        assertEquals(expectedFat, fat);
    }

}
