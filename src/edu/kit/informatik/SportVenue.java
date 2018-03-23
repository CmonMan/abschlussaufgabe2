package edu.kit.informatik;

/**
 * Sportstätten Klasse welche ein Sportstätten Objekt darstellt.
 */
public class SportVenue {
    private String location;
    private String sportsVenueName;
    private int yearOfOpening;
    private int numberOfSeats;
    private String id;

    /**
     * Konstruktor einer Sportstätte
     * @param id id der Sportstätte
     * @param place Ort
     * @param sportsVenueName Name der Sporstätte
     * @param yearOfOpening Eröffnungsjahr
     * @param numberOfSeats Sitzplätze
     */
    public SportVenue(String id, String place, String sportsVenueName, int yearOfOpening, int numberOfSeats) {
        this.id = id;
        this.location = place;
        this.sportsVenueName = sportsVenueName;
        this.yearOfOpening = yearOfOpening;
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Getter um die Anzahl der Sitzplätze zu bekommen
     * @return Anzahl der Sitzplätze
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Getter um den Ort der Sportstätte zu bekommen
     * @return Ort der Sportstätte
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter um die ID der Sportstätte zu bekommen
     * @return ID der Sportstätte
     */
    public String getID() {
        return id;
    }
}

