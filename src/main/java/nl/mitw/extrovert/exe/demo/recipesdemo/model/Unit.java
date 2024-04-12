package nl.mitw.extrovert.exe.demo.recipesdemo.model;

/**
 * Represents a measuring unit that can only have a set of values
 *
 * @author B.J. Falkena
 */
public enum Unit {
    TEASPOON("teaspoon"),
    TABLESPOON("tablespoon"),
    GRAM("gram"),
    MILLILITERS("milliliters");

    private final String name;

    Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}