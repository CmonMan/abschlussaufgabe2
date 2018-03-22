package edu.kit.informatik;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse die ein Land repräsentiert im Verwaltungssystem
 */
public class Country{
    private String countryName;
    private Map<String, SportVenue> sportsVenues;
    private IOC ioc;
    private Map<Integer, Athlete> athletesOfCountry;

    /**
     * Konstruktor für die Country Klasse
     * @param iocID IOC ID
     * @param iocCode IOC Code
     * @param year Festlegungsjahr
     */
    public Country(int iocID, String iocCode, int year, String countryName) {
        this.countryName = countryName;
        this.sportsVenues = new HashMap<>();
        this.ioc = new IOC(iocID, iocCode, year);
        this.athletesOfCountry = new HashMap<>();
    }


    /**
     * Methode um dem Land eine Sportstätte hinzuzufügen
     * @param sportsVenueID ID der Sportstätte
     * @param place Ort der Sportstätte
     * @param sportsVenueName Name der Sportstätte
     * @param yearOfOpening Eröffnungsjahr
     * @param amountOfSeats Anzahl der Sitzplätze
     */
    public void addSportsVenue(String sportsVenueID, String place, String sportsVenueName, int yearOfOpening,
                               int amountOfSeats) {
        sportsVenues.put(sportsVenueID, new SportVenue(sportsVenueID, place, sportsVenueName, yearOfOpening,
                amountOfSeats));
    }

    /**
     * Methode gibt die String liste an Sportstätten sortiert zurück
     * @return Liste der Sportstätten
     */
    public String listSportsVenues() {
        return SortSportsVenueBySeats.sortBySeats(sportsVenues);
    }

    /**
     * Hinzufügen eines Athleten zum Land
     * @param athleteID ID des Athleten
     * @param preName Vorname
     * @param surName Nachname
     */
    public void addAthlete(int athleteID, String preName, String surName) {
        athletesOfCountry.put(athleteID, new Athlete(athleteID, preName, surName));
    }

    /**
     * Methode prüft ob der Athlet in dem Land bereits initialisiert wurde.
     * @param athleteID ID es Athleten und somit schlüssel
     * @return true falls er existiert, false falls nicht
     */
    public boolean athleteExist(int athleteID) {
        return athletesOfCountry.containsKey(athleteID);
    }

    /**
     * Getter für den Namen des Landes
     * @return Name des Landes
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Getter Methode für die IOC-Codes des Landes
     * @return IOC Objekt
     */
    public IOC getIOC() {
        return ioc;
    }

    /**
     * Es werden die Sportstätten eines Landes zurückgegeben.
     * Somit kann Exception Handling in einer Klasse durchgeführt werden und ist nicht überall verteilt.
     * @return Sporstätten des Landes
     */
    public Map<String, SportVenue> getSportsVenues() {
        return sportsVenues;
    }

}
