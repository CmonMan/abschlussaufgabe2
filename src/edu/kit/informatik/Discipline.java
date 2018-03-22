package edu.kit.informatik;


import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Discipline {
    private String nameOfDiscipline;
    private Map<Integer, Athlete> athletesOfTheDiscipline;


    /**
     * Konstruktor der Disziplin Klasse
     * @param discipline Name der Disziplin
     */
    public Discipline(String discipline) {
        this.nameOfDiscipline = discipline;
        athletesOfTheDiscipline = new HashMap<>();
    }

    /**
     * Methode fügt einen Athleten zu der Disziplin hinzu
     * @param athleteID Id des Athleten
     * @param preName Vorname
     * @param surName Nachname
     */
    public void addAthleteToDiscipline(int athleteID, String preName, String surName) {
        athletesOfTheDiscipline.put(athleteID, new Athlete(athleteID, preName, surName));
    }

    /**
     * Methode prüft ob der Athlet bereits in der Disziplin existiert
     * @param athleteID ID des Athleten
     * @return true falls er existiert, false falls nicht
     */
    public boolean athleteExistInDiscipline(int athleteID) {
        return athletesOfTheDiscipline.containsKey(athleteID);
    }

    public String summaryAthletes() {
        return SortAthletesOfDiscipline.sortAthletes(athletesOfTheDiscipline);
    }
}
