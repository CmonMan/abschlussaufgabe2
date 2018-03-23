package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Die Klasse sortiert mithilfe der static Athleten einer Sportart und Disziplin alphabetisch.
 */
public class SortAthletesOfDiscipline {

    /**
     * Methode sortiert alphabetisch die Sportler der Disziplin.
     * @param mapOfAthletes Athleten einer Disziplin
     * @return Liste der Athleten
     */
    public static String sortAthletes(Map<Integer, Athlete> mapOfAthletes) {
        ArrayList<Athlete> listOfAthletes = new ArrayList<>();

        for (Integer id : mapOfAthletes.keySet()) {
            listOfAthletes.add(mapOfAthletes.get(id));
        }
        listOfAthletes.sort(Collections.reverseOrder());

        String athleteList = "";
        for (Athlete athlete : listOfAthletes) {
            athleteList += athlete.getId() + " " + athlete.getPreName() + " " + athlete.getSurName() + " " +
                    athlete.getMedal();
        }

        return athleteList;
    }
}
