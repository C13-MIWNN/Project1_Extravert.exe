package nl.mitw.extrovert.exe.demo.recipesdemo.model;

/**
 * Represents a measuring unit that can only have a set of values
 *
 * @author B.J. Falkena
 */


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

    public String getValue() {
        return value;
    }
}