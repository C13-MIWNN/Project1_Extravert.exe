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

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @ManyToMany
    private Set<Tag> tags;


    public Recipe() {

    }

    public void addIngredient(Ingredient ingredient, int amount) {
        RecipeIngredient recipeIngredient = new RecipeIngredient(this, ingredient, amount);
        ingredients.add(recipeIngredient);
        ingredient.getRecipes().add(recipeIngredient);
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
}
