package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the relation between a recipe and an ingredient
 *
 * @author B.J. Falkena
 */

@Getter
@Setter
@NoArgsConstructor
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

    public double calculateCarbsForAmount() {
        return calculateMacroForAmount(ingredient.getCarbohydrate());
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
}