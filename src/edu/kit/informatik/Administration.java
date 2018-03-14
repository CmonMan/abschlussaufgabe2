package edu.kit.informatik;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Administration {

    private Map<String, Admin> admins;
    private boolean adminLoggedIn = false;

    public Administration() {
        this.admins = new HashMap<>();
    }

    /**
     * Methode zum erstellen eines Admins
     * @param preName Vorname des Admins
     * @param surName Nachname des Admins
     * @param userName Benutzername des Admins
     * @param password Passwort des Accounts
     * @throws InputException falls ein Admin eingeloggt ist oder dieser Admin schon existiert
     */
    public void addAdmin(String preName, String surName, String userName, String password) throws InputException{
        if (adminLoggedIn) {
            throw new InputException("an admin is logged in. Please log out.");
        } else if (admins.containsKey(userName)) {
            throw new InputException("this admin already exists.");
        }
        admins.put(userName, new Admin(preName, surName, password));
    }

    /**
     * Die Methode um sich als Administrator anzumelden.
     * @param userName Benutzername des Admins
     * @param password Passwort des Admin Accounts
     * @throws InputException falls ein Admin angemeldet, Admin nicht registriert, falsches Passwort
     */
    public void loginAdmin(String userName, String password) throws InputException{
        if (adminLoggedIn) {
            throw new InputException("an admin is logged in. Please log out.");
        } else if (!admins.containsKey(userName)) {
            throw new InputException("the admin is not registered. Please add this admin first.");
        } else if (!admins.get(userName).getPassword().equals(password)) {
            throw new InputException("enter the correct password.");
        }
        adminLoggedIn = true;
    }

    /**
     * Methode um sich als Administrator aus zu loggen.
     * @throws InputException falls kein Administrator angemeldet ist
     */
    public void logoutAdmin() throws InputException{
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        }
        adminLoggedIn = false;
    }
}
