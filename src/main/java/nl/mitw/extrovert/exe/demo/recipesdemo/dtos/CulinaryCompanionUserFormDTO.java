package nl.mitw.extrovert.exe.demo.recipesdemo.dtos;

/**
 * Nadine Beck
 * Omschrijving
 */
public class CulinaryCompanionUserFormDTO {
    private String name;

    private String password;
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
