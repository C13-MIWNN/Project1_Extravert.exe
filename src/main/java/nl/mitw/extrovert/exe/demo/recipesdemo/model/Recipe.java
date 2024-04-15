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
    private String preparationTime;
    private int numberOfServings;

    @ElementCollection @OrderColumn
    private List<String> recipeSteps = new ArrayList<>();

    @ManyToMany
    private Set<Ingredient> ingredients;

    @ManyToMany
    private Set<Tag> tags;


    public Recipe() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
}
