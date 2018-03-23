package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Die Klasse sortiert mithilfe der static Methode alle Länder nach ihren gewonnenen Medaillen.
 */
public class SortCountriesByMedals {

    /**
     * Sortiert die Länder nach gewonnenen Medaillen
     * @param countries eine Map an Ländern
     * @return Liste als String des ewigen Medaillenspiegels sortiert nach der Anzahl gewonnenenr Gold, SIlber, Bronze
     * Medaillen
     */
    public static String olympicMedalTable(Map<String, Country> countries) {
        Comparator comparator = comparatorForMedals();
        ArrayList<Country> countriesToSort = new ArrayList<>();

        //Arrayliste an Ländern wird aus der Map extrahiert
        for (String countryName : countries.keySet()) {
            countriesToSort.add(countries.get(countryName));
        }

        Collections.sort(countriesToSort, comparator);

        int placement = 1;
        String medalListOfCountries = "";

        //String Konkatenation des Medaillen Spiegels
        for (Country country : countriesToSort) {
            medalListOfCountries += placement + " " + country.getIOC().getIocID() + " " + country.getIOC().getIocCode()
                    + " " + country.getCountryName() + " " + country.getGold() + " " + country.getSilver() + " " +
                    country.getBronze() + " " + country.getAmountOfMedals() + "\n";
            placement++;
        }
        return medalListOfCountries;
    }

    /**
     * Methode erstellt einen comparator der erst nach Gold Medaillen dann Silber und letztendlich Bronze Länder
     * vergleicht.
     * @return comparator
     */
    private static Comparator comparatorForMedals() {
        Comparator<Country> comparator = Comparator.comparing(Country::getGold, Comparator.reverseOrder())
                .thenComparing(Country::getSilver, Comparator.reverseOrder())
                .thenComparing(Country::getBronze, Comparator.reverseOrder());
        return comparator;
    }
}
