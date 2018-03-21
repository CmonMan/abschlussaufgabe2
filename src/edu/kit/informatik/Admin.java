package edu.kit.informatik;

/**
 * Die Klasse repräsentiert das Objekt eines Administrators der Nutzername wird als Key, da er eindeutig ist in der
 * Hashmap abgespeichert wird und somit nicht noch ein weiteres mal in dem Objekt.
 */
public class Admin extends Person {

    private String password;
    private String preName;
    private String surName;
    /**
     * Konstruktor für einen Administrator
     * @param preName Vorname
     * @param surName Nachname
     * @param password Passwort
     */
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
