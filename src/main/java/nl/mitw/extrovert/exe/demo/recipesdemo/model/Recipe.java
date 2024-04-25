package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Nadine Beck
 * Represents a recipe that can have ingredients and tags
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "longtext")
    private String preparation;
    private String preparationTime;
    private int numberOfServings;

    @ElementCollection @OrderColumn
    private List<String> recipeSteps = new ArrayList<>();

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @ManyToMany
    private Set<Tag> tags;

    public enum MacroType {
        CARBS,
        FAT,
        PROTEIN
    }

    public double calculateRecipeCarbs() {
        return calculateRecipeMacro(MacroType.CARBS);
    }

    public double calculateRecipeFat() {
        return calculateRecipeMacro(MacroType.FAT);
    }

    public double calculateRecipeProtein() {
        return calculateRecipeMacro(MacroType.PROTEIN);
    }

    public double calculateRecipeMacro(MacroType macroType) {
        double recipeMacro = 0;

        for (RecipeIngredient recipeIngredient: ingredients) {
            switch (macroType){
                case CARBS:
                    recipeMacro += recipeIngredient.calculateCarbsForAmount();
                    break;
                case FAT:
                    recipeMacro += recipeIngredient.calculateFatForAmount();
                    break;
                case PROTEIN:
                    recipeMacro += recipeIngredient.calculateProteinForAmount();
                    break;

            }
        }
        return Math.round(recipeMacro / numberOfServings);
    }

    public int calculateRecipeCaloriesPerPortion() {
        return (int) (4 * calculateRecipeProtein() + 4 * calculateRecipeCarbs() + 9 * calculateRecipeFat());
    }
}
