package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 *Die Klasse sortiert mithilfe der static Methode alle Sportstätten nach Anzahl ihrer Sitze und danach der ID.
 */
public class SortSportsVenueBySeats {

    /**
     * Sortiert die Sportstätten nach der Anzahl der Sitzplätze und weiter nach der ID
     * @param sportsVenues Sportstätten des Landes
     * @return einen String der die Liste der sortierten Sportstätten beinhaltet
     */
    public static String sortBySeats(Map<Integer, SportVenue> sportsVenues) {
        Comparator<SportVenue> comparator = comparatorForSeats();
        ArrayList<SportVenue> listOfSportVenues = new ArrayList<>();
        // Die Arrayliste wird mit Sportstätten gefüllt
        for (Integer key: sportsVenues.keySet()) {
            listOfSportVenues.add(sportsVenues.get(key));
        }
        listOfSportVenues.sort(comparator);

        String sportVenueList = "";
        int placement = 1;
        //String Konkatenation um eine Liste an Sportstätten zurückzugeben
        for (SportVenue venue : listOfSportVenues) {
            sportVenueList += "(" + placement + " " + venue.getID() + " " + venue.getLocation() + " "
                    + venue.getNumberOfSeats() + ")" + "\n";
            placement++;
        }
        return sportVenueList;
    }

    /**
     * Methode erstellt einen comparator der erst nach Anzahl der Sitzplätze und dann nach der ID vergleicht
     * @return comparator
     */
    private static Comparator<SportVenue> comparatorForSeats() {
        return Comparator.comparing(SportVenue::getNumberOfSeats,
                Comparator.reverseOrder()).thenComparing(SportVenue::getID, Comparator.reverseOrder());
    }
}