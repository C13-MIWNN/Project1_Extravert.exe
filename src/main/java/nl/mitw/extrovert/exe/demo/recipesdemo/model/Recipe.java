package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import java.util.Set;

/**
 * Nadine Beck
 * Omschrijving
 */
public class Recipe {
    private String name;
    private String preparation;
    private String tag;


    public Recipe(String name, String preparation, String tag) {
        this.name = name;
        this.preparation = preparation;
        this.tag = tag;
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
