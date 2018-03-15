package edu.kit.informatik;

public class SportVenue implements Comparable<SportVenue>{
    private String location;
    private String sportsVenueName;
    private int yearOfOpening;
    private int numberOfSeats;
    private String ID;

    /**
     * Konstruktor einer Sportstätte
     * @param place Ort
     * @param sportsVenueName Name der Sporstätte
     * @param yearOfOpening Eröffnungsjahr
     * @param numberOfSeats Sitzplätze
     */
    public SportVenue(String ID, String place, String sportsVenueName, int yearOfOpening, int numberOfSeats) {
        this.ID = ID;
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
     * Methode um zwei Sportstätten nach der Anzahl ihrer Sitzplätze zu vergleichen.
     * @param other die Sportstätte mit der verglichen wird
     * @return 1 falls Sportstätte mehr Sitzplätze hat, -1 wenn nicht,  dritter return für nächste Vergleichsbedingung
     */
    @Override
    public int compareTo(SportVenue other) {
        if(this.numberOfSeats > other.getNumberOfSeats()) {
            return 1;
        } else if(this.numberOfSeats > other.getNumberOfSeats()) {
            return -1;
        } else return this.ID.compareTo(other.getID());
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
        return ID;
    }
}

