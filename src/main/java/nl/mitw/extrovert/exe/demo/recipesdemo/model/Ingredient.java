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

    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipes = new ArrayList<>();


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

    @Override
    public String toString() {
        return name;
    }
}
