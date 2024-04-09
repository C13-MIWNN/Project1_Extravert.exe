package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Nadine Beck
 * Omschrijving
 */

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long IngredientId;
    private String name;


}
