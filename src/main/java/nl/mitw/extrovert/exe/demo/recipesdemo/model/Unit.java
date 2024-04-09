package nl.mitw.extrovert.exe.demo.recipesdemo.model;

/**
 * hier de opdracht die je gaat maken
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