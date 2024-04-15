package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Nadine Beck
 * Represents an ingredient that can belong to one or many recipes
 */

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;
    private String name;


    private Unit unit;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipes = new ArrayList<>();

    public Ingredient(Long ingredientId, String name, Unit unit, List<RecipeIngredient> recipes) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.unit = unit;
        this.recipes = recipes;
    }

    public Ingredient() {

    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<RecipeIngredient> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeIngredient> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return name;
    }
}
