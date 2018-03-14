package edu.kit.informatik;

public class SportVenue{
    //TODO: schauen was mit den Variablen hier anzustellen ist
    private String location;
    private String sportsVenueName;
    private int yearOfOpening;
    private int numberOfSeats;

    /**
     * Konstruktor einer Sportstätte
     * @param place Ort
     * @param sportsVenueName Name der Sporstätte
     * @param yearOfOpening Eröffnungsjahr
     * @param numberOfSeats Sitzplätze
     */
    public SportVenue(String place, String sportsVenueName, int yearOfOpening, int numberOfSeats) {
        this.location = place;
        this.sportsVenueName = sportsVenueName;
        this.yearOfOpening = yearOfOpening;
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}

