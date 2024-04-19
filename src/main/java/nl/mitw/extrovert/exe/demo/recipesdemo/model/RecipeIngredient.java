package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Represents the relation between a recipe and an ingredient
 *
 * @author B.J. Falkena
 */

@Entity
public class RecipeIngredient {
    private static final int GRAMS_IN_HUNDRED_GRAMS = 100;

    @Id
    @GeneratedValue
    private Long recipeIngredientId;

    private int amount;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne Recipe recipe;

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, int amount) {
    }

    public RecipeIngredient() {

    }

    public double calculateCarbsForAmount() {
        return calculateMacroForAmount(ingredient.getCarbohydrates());
    }

    public double calculateFatForAmount() {
        return calculateMacroForAmount(ingredient.getFat());

    }

    public double calculateProteinForAmount() {
        return calculateMacroForAmount(ingredient.getProtein());
    }

    public double calculateMacroForAmount(int macro) {
        if (ingredient.getUnit() == Unit.GRAM || ingredient.getUnit() == Unit.ML) {
            return ((double) amount / GRAMS_IN_HUNDRED_GRAMS) * macro;
        }
        return amount * macro;
    }

    @Override
    public String toString() {
        return String.format("%d %s", this.amount, this.ingredient);
    }

    public Long getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(Long recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}