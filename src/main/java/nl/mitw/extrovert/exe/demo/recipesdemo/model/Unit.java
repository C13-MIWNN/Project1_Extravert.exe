package nl.mitw.extrovert.exe.demo.recipesdemo.model;

import lombok.Getter;

/**
 * Represents a measuring unit that can only have a set of values
 *
 * @author B.J. Falkena
 */


@Getter
public enum Unit {
    TSP("teaspoons"),
    TBS("tablespoons"),
    GRAM("grams"),
    ML("milliliters"),
    PIECE(" ");

    private final String value;

    Unit(String value) {
        this.value = value;
    }
}