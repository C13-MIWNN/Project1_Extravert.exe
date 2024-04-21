package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Nadine Beck
 * Represents a recipe that can have ingredients and tags
 */

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

    public Recipe() {

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
        long roundNumber = Math.round(recipeMacro);
        return roundNumber / (double) numberOfServings;
    }

    public double calculateRecipeCaloriesPerPortion() {
        return (4 * calculateRecipeProtein() + 4 * calculateRecipeCarbs() + 9 * calculateRecipeFat());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfPortions) {
        this.numberOfServings = numberOfPortions;
    }

    public List<String> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<String> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
