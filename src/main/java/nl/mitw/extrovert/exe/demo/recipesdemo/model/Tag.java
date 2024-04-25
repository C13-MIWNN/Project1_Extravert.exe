package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.JSONUtils;

import java.util.Set;

/**
 * Represents a tag that can belong to one or many recipes
 *
 * @author B.J. Falkena
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tag {
    @Id @GeneratedValue
    private Long tagId;


    private String nameTag;

    @ManyToMany(mappedBy = "tags")
    private Set<Recipe> recipes;

    @Override
    public String toString() {
        return nameTag;
    }
}