package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Nadine Beck
 * Represents an ingredient that can belong to one or many recipes
 */

@Getter
@Setter
@NoArgsConstructor
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

    public int calculateCalories() {
        return  FACTOR_PROTEIN_TO_KCAL * protein +
                FACTOR_CARBS_TO_KCAL * carbohydrate +
                FACTOR_FAT_TO_KCAL * fat;
    }

    @Override
    public String toString() {
        return name;
    }
}
