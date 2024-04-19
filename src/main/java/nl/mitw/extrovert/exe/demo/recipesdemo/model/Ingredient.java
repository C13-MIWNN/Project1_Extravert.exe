package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Nadine Beck
 * Represents an ingredient that can belong to one or many recipes
 */

@Entity
public class Ingredient {
    public static final int FACTOR_PROTEIN_TO_KCAL = 4;
    public static final int FACTOR_CARBS_TO_KCAL = 4;
    public static final int FACTOR_FAT_TO_KCAL = 9;

    @Id @GeneratedValue
    private Long ingredientId;
    private String name;
    private int protein;
    private int carbohydrate;
    private int fat;
    private Unit unit;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipes = new ArrayList<>();

    public Ingredient(Long ingredientId, String name, Unit unit,
                      int protein, int carbohydrate, int fat,
                      List<RecipeIngredient> recipes) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.unit = unit;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.recipes = recipes;
    }

    public Ingredient(String name) {
        this(null, name, null, 0, 0, 0, null);
    }

    public Ingredient() {
    }

    public int calculateCalories() {
        return  FACTOR_PROTEIN_TO_KCAL * protein +
                FACTOR_CARBS_TO_KCAL * carbohydrate +
                FACTOR_FAT_TO_KCAL * fat;
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
