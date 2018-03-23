package edu.kit.informatik;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse die ein Land repräsentiert im Verwaltungssystem
 */
public class Country {
    private String countryName;
    private Map<Integer, SportVenue> sportsVenues;
    private IOC ioc;
    private Map<Integer, Athlete> athletesOfCountry;
    private int gold;
    private int silver;
    private int bronze;

    /**
     * Konstruktor für die Country Klasse
     * @param iocID IOC ID
     * @param iocCode IOC Code
     * @param year Festlegungsjahr
     * @param countryName Name des Landes
     */
    public Country(int iocID, String iocCode, int year, String countryName) {
        this.countryName = countryName;
        this.sportsVenues = new HashMap<>();
        this.ioc = new IOC(iocID, iocCode, year);
        this.athletesOfCountry = new HashMap<>();
        gold = 0;
        silver = 0;
        bronze = 0;
    }


    /**
     * Methode um dem Land eine Sportstätte hinzuzufügen
     * @param sportsVenueID ID der Sportstätte
     * @param place Ort der Sportstätte
     * @param sportsVenueName Name der Sportstätte
     * @param yearOfOpening Eröffnungsjahr
     * @param amountOfSeats Anzahl der Sitzplätze
     */
    public void addSportsVenue(int sportsVenueID, String place, String sportsVenueName, int yearOfOpening,
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
    public Map<Integer, SportVenue> getSportsVenues() {
        return sportsVenues;
    }

    /**
     * Methode addiert die Medallien eines Wettkampfes zu dem Land.
     * @param gold Gold Medaille
     * @param silver Silber Medaille
     * @param bronze Bronze Medaille
     */
    public void addMedalToCountry(int gold, int silver, int bronze) {
        this.gold += gold;
        this.silver += silver;
        this.bronze += bronze;
    }

    /**
     * Getter für den Namen des Landes
     * @return Name des Landes
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Getter für die Gold Medaillen des Landes.
     * @return Gold Medaillen
     */
    public int getGold() {
        return gold;
    }

    /**
     * Getter für die Silber Medaillen des Landes.
     * @return Silber Medaillen
     */
    public int getSilver() {
        return silver;
    }

    /**
     * Getter für die Bronze Medaillen des Landes.
     * @return Bronze Medaillen
     */
    public int getBronze() {
        return bronze;
    }

    /**
     * Methode um die Anzahl der Medaillen eines Landes zu bekommen.
     * @return Anzahl der Medaillen
     */
    public int getAmountOfMedals() {
        return gold + silver + bronze;
    }
}
