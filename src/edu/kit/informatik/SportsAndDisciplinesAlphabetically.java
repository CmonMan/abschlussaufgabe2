package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Klasse beinhaltet die statische Methode um Sportarten und Disziplinen Alphabetisch zu sortieren
 */
public class SportsAndDisciplinesAlphabetically {

    /**
     * Die Methode sortiert eine Map an Sportarten und Disziplinen alphabetisch
     * @param sports die Sportarten welche zu untersuchen sind
     * @return eine Liste an Sportarten und Disziplinen
     */
    public static String listSportsAndDisciplines(Map<String, Sport> sports) {
        ArrayList<Sport> listOfSports = new ArrayList<>();

        //for schleife fügt einer Liste die Sportarten hinzu und sortiert die Sportdisziplinenen innerhalb der Sportklasse
        for (String sport : sports.keySet()) {
            listOfSports.add(sports.get(sport));
        }

        listOfSports.sort(Comparator.comparing(Sport:: getNameOfSport));

        String sportList = "";
        // Schleife iteriert durch einzelne Sportarten und erstellt eine Liste von den Sportarten mit dazugehörigen Disziplinen
        for (Sport sport: listOfSports) {
            sportList += sport.getSportAndDisciplines();
        }
        return sportList;
    }
}
