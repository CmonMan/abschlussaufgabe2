package edu.kit.informatik;

public class Admin extends Person {

    private String password;

    public Admin(String preName, String surName, String password) {
        this.preName = preName;
        this.surName = surName;
        this.password = password;
    }

    /**
     * Getter um einen abgleich des eingegebenen Passwortes durchzuführen
     * @return Passwort des Administrators
     */
    public String getPassword() {
        return password;
    }
}
