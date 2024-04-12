package nl.mitw.extrovert.exe.demo.recipesdemo.model;

/**
 * Represents a measuring unit that can only have a set of values
 *
 * @author B.J. Falkena
 */


public enum Unit {
    tsp("teaspoon"),
    tbs("tablespoon"),
    gram("gram"),
    ml("milliliters"),
    piece("piece");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}