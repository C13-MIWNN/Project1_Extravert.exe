package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
    private String tag;


    public Recipe(String name, String preparation, String tag) {
        this.name = name;
        this.preparation = preparation;
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
}
