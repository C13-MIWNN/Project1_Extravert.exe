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
    private int protein;
    private int carbohydrate;
    private int fat;
    private Unit unit;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipes = new ArrayList<>();

    public Ingredient(Long ingredientId, String name, Unit unit, List<RecipeIngredient> recipes,
                      int protein, int carbohydrate, int fat) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.unit = unit;
        this.recipes = recipes;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }

    public Ingredient() {
    }

    public int calculateCalories() {
        return  4 * protein + 4 * carbohydrate + 9 * fat;
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

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrates() {
        return carbohydrate;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrate= carbohydrates;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return name;
    }
}
