package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import nonapi.io.github.classgraph.json.JSONUtils;

import java.util.Set;

/**
 * Represents a tag that can belong to one or many recipes
 *
 * @author B.J. Falkena
 */
@Entity
public class Tag {
    @Id @GeneratedValue
    private Long tagId;


    private String nameTag;

    @ManyToMany(mappedBy = "tags")
    private Set<Recipe> recipes;

    public Tag() {

    }


    @Override
    public String toString() {
        return nameTag;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}