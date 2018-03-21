package edu.kit.informatik;

import java.util.*;

/**
 * Die Administrations Klasse stellt das Verwaltungssystem dar. Über diese wird das Exception Handling betrieben und
 * es werden Informationen weitergegeben oder für das Verwaltungssyste wichtige abgespeichert.
 */
public class Administration {

    private HashMap<String, Sport> sports;
    private Map<String, Admin> admins;
    private Map<String, Country> countries;
    private boolean adminLoggedIn = false;

    /**
     * Konstruktor der Klasse instanziiert die einzelnen Verwaltungseinheiten.
     */
    public Administration() {
        this.admins = new HashMap<>();
        this.countries = new HashMap<>();
        this.sports = new HashMap<>();
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
    public void addSportsVenue(String sportsVenueID, String countryName, String place, String sportsVenueName,
                               int yearOfOpening, int numberOfSeats) throws InputException{
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        } else if (!countries.containsKey(countryName)) {
            throw new InputException("you need to initialize the IOC code and country first");
        } else if (countries.get(countryName).getSportsVenues().containsKey(sportsVenueID)) {
            throw new InputException("the sports venue you want to add already exists.");
        }

        countries.get(countryName).addSportsVenue(sportsVenueID, place, sportsVenueName, yearOfOpening, numberOfSeats);
    }


    /**
     * Methode um die Sporstätten eines Landes herauszubekommen
     * @param countryName Name des Landes
     * @return Es wird eine Liste als String an Sportstätten sortiert nach den Sitzplätzen bzw. IDs zurückgegeben
     * @throws InputException falls das Land keine Sportstätten besitzt
     */

    public String listSportsVenues(String countryName) throws InputException {
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        } else if (!countries.containsKey(countryName)) {
            throw new InputException("no sport venue in this country");
        } else if (countries.get(countryName).getSportsVenues().isEmpty()) {
            throw new InputException("there is no sport venue in this country");
        }
        return countries.get(countryName).listSportsVenues();
    }

    /**
     * Methode um eine Sportart und Disziplin hinzuzufügen
     * @param sport Name der Sportart
     * @param discipline Name der Disziplin
     * @throws InputException falls die Disziplin bereits existiert
     */
    public void addOlympicSport(String sport, String discipline) throws InputException {
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        } else if (!sports.containsKey(sport)) {
            Sport actualSport = new Sport(sport);
            sports.put(sport, actualSport);
            actualSport.addDiscipline(discipline);
            return;
        }
        Sport actualSport = sports.get(sport);
        if(!actualSport.addDiscipline(discipline)) {
            throw new InputException("the discipline already exists");
        }
        actualSport.addDiscipline(discipline);
    }

    /**
     * Methode um die Sportarten sowie die dazugehörigen Disziplinen zu als Liste in einem String zurückzugeben
     * @return einen Sportarten und Disziplinen String
     * @throws InputException falls Admin nicht angemeldet oder noch keine Sportarten hinzugefügt
     */
    public String listSportsAndDisciplines() throws InputException{
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        } else if (sports.isEmpty()) {
            throw new InputException("you need to add a sport and discipline first");
        }
        return SportsAndDisciplinesAlphabetically.listSportsAndDisciplines(sports).trim();
    }


    /**
     * Methode um einem Land einen IOC Code zuzuweisen.
     * @param iocID IOC ID
     * @param iocCode IOC Code
     * @param countryName Name des Landes
     * @param year Festlegungsjahr
     * @throws InputException falls Admin nicht angemeldet oder das Land schon existiert, da das Land nur erstellt
     * werden kann wenn ein IOC code eingegeben wird.
     */
    public void addIOCCode(int iocID, String iocCode, String countryName, int year) throws InputException {
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        } else if (countries.containsKey(countryName)) {
            throw new InputException("the country already exists with the IOC code");
        }
        countries.put(countryName, new Country(iocID, iocCode, year, countryName));
    }

    /**
     * Methode gibt einen String mit einer Liste an IOC Codes sortiert nach deren Festlegungsdatum und ID zurück. Man
     * musst nicht prüfen ob es Länder ohne IOC Codes gibt, da diese niemals erstellt werden können.
     * @return String Liste mit sortierten IOC Codes.
     * @throws InputException wenn kein Admin angemeldet oder keine Länder existieren
     */
    public String listIOCCodes() throws InputException {
        if (!adminLoggedIn) {
            throw new InputException("there is no admin logged in.");
        } else if (countries.isEmpty()) {
            throw new InputException("there are no countries and IOC Codes set");
        }
        return ListOfCountriesAndIOCCodesBySettingYear.listCountriesAndIOC(countries);
    }
}
