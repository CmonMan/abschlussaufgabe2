package edu.kit.informatik;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Administration {

    private Map<String, Admin> admins;
    private Map<String, Country> countries;
    private boolean adminLoggedIn = false;

    public Administration() {
        this.admins = new HashMap<>();
        this.countries = new HashMap<>();
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

    /**
     * Methode um einem Land eine Sporstätte hinzuzufügen
     * @param sportsVenueID ID
     * @param countryName Land in dem sich die Sportstätte befindet
     * @param place Ort der Sportstätte
     * @param sportsVenueName Name
     * @param yearOfOpening Eröffnungsjahr
     * @param numberOfSeats Anzahl der Sitzplätze
     * @throws InputException falls die Sportstätte schon existiert
     */
    public void addSportsVenue(int sportsVenueID, String countryName, String place, String sportsVenueName,
                               int yearOfOpening, int numberOfSeats) throws InputException{
        /** Falls das Land noch nicht existiert wird ein neues Land erstellt. Es muss dann auch nicht geprüft werden
         * ob es die Sportstätte schon gibt, da keine Instanz des Landes mit diesem Namen existiert und somit
         * keine Möglichkeit besteht, dass die Sportstätte existiert.
         */
        if (!countries.containsKey(countryName)) {
            countries.put(countryName, new Country());
        } else if (countries.get(countryName).getSportsVenues().containsKey(sportsVenueID)) {
            throw new InputException("the sports venue you want to add already exists.");
        }

        countries.get(countryName).addSportsVenue(sportsVenueID, place, sportsVenueName, yearOfOpening, numberOfSeats);
    }

    /**
     * Methode um die Sporstätten eines Landes herauszubekommen
     * @param countryName Name des Landes
     * @return Es wird eine Liste an Sportstätten sortiert nach den Sitzplätzen zurückgegeben
     * @throws InputException falls das Land keine Sportstätten besitzt
     */
    //TODO: testen ob bei einem land das nicht initialisiert wurde Programm abstürzt (vermutlich nicht da nach Key gefragt wurde und abbricht)
    public String listSportsVenues(String countryName) throws InputException {
        if (!countries.containsKey(countryName)) {
            throw new InputException("no sport venue in this country");
        } else if (countries.get(countryName).getSportsVenues().size() == 0) {
            throw new InputException("there is no sport venue in this country");
        }
        //TODO: herausfinden wegen Sortierung und wie der return befehl dann aussehen muss
        return null;
    }
}
