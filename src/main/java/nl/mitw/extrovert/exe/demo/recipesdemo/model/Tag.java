package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Hier worden tags aangemaakt
 *
 * @author B.J. Falkena
 */
@Entity
public class Tag {
    @Id @GeneratedValue
    private Long tagId;


    private String nameTag;

    public Tag(Long tagId, String nameTag) {
        this.tagId = tagId;
        this.nameTag = nameTag;
    }

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
}