package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 *
 */
public class SortAthletesOfDiscipline {

    /**
     * @param mapOfAthletes
     * @return
     */
    public static String sortAthletes(Map<Integer, Athlete> mapOfAthletes) {
        ArrayList<Athlete> listOfAthletes = new ArrayList<>();

        for (Integer id : mapOfAthletes.keySet()) {
            listOfAthletes.add(mapOfAthletes.get(id));
        }
        Collections.sort(listOfAthletes);

        String athleteList = "";
        for (Athlete athlete : listOfAthletes) {
            athleteList += athlete.getId() + " " + athlete.getPreName() + " " + athlete.getSurName() + " " +
                    athlete.getMedal();
        }

        return athleteList;
    }
}
