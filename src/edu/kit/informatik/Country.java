package edu.kit.informatik;

import java.util.HashMap;
import java.util.Map;

public class Country {
    private Map<String, SportVenue> sportsVenues;

    /**
     * Konstruktor für die Country Klasse
     */
    public Country() {
        this.sportsVenues = new HashMap<>();
    }

    /**
     * Es werden die Sportstätten eines Landes zurückgegeben.
     * Somit kann Exception Handling in einer Klasse durchgeführt werden und ist nicht überall verteilt.
     * @return Sporstätten des Landes
     */
    public Map<String, SportVenue> getSportsVenues() {
        return sportsVenues;
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

    public String listSportsVenues() {
        return SortSportsVenueBySeats.sortBySeats(sportsVenues);                                                        //TODO:Kommentar
    }
}
