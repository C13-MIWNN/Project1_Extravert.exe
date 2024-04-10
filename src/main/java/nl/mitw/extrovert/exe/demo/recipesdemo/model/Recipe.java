package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

/**
 * Nadine Beck
 * Omschrijving
 */

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;
    private String name;
    private String preparation;
    private String preparationTime;
    private String tag;


    @ManyToMany
    private Set<Ingredient> ingredients;


    public Recipe(Long recipeId, String name, String preparation, String preparationTime, String tag) {
        this.recipeId = recipeId;
        this.name = name;
        this.preparation = preparation;
        this.preparationTime = preparationTime;
        this.tag = tag;
    }

    public Recipe() {

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
}
