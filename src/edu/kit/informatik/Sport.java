package edu.kit.informatik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Sport Klasse mit dem Namen der Sportart sowie den dazugehörigen Disziplinen
 */
public class Sport {
    private String nameOfSport;
    private Map<String, Discipline> disciplines;

    /**
     * Konstruktor um eine neue Sportart zu initialisieren
     * @param nameOfSport Name des initialisierten Sportes
     */
    public Sport(String nameOfSport) {
        this.nameOfSport = nameOfSport;
        this.disciplines = new HashMap<>();
    }

    /**
     * Hinzufügen einer Disziplin zu der Sportart
     * @param discipline Name der Sportdisziplin
     */
    public void addDiscipline(String discipline) {
        disciplines.put(discipline, new Discipline(discipline));
    }

    /**
     * Methode prüft ob eine bestimmte Sportdisziplin existiert.
     * @param discipline geprüfte Disziplin
     * @return true falls es existiert, false falls nicht
     */
    public boolean disciplineExists(String discipline) {
        return disciplines.containsKey(discipline);
    }

    /**
     * Disziplinen werden alphabetisch sortiert zurückgegeben
     * @return Sortierte Disziplinen
     */
    public ArrayList<String> getSortedDisciplines() {
        ArrayList<String> listOfDisciplines = new ArrayList<>(disciplines.keySet());
        listOfDisciplines.sort(String.CASE_INSENSITIVE_ORDER);
        return listOfDisciplines;
    }

    /**
     * Sportart wird mit den dazugehörigen Disziplinen konkateniert und zurück gegeben
     * @return String der Konkatenation von Sportart und dazugehörigen Disziplinen in einer Liste
     */
    public String getSportAndDisciplines() {
        ArrayList<String> listDisciplines = getSortedDisciplines();
        String sportAndDisciplines = "";
        for (String discipline : listDisciplines) {
            sportAndDisciplines += nameOfSport + " " + discipline + "\n";
        }
        return sportAndDisciplines.trim();
    }

    /**
     * Die Methode sucht die Sportdisziplin des Sportlers aus der Hashmap und gibt an die Methode zum hinzufügen des
     * Sportlers in der Disziplin Klasse die Daten des Sportlers weiter.
     * @param athleteID ID des Athleten
     * @param preName Vorname
     * @param surName Nachname
     * @param discipline Disziplin
     */
    public void addAthleteInDiscipline(int athleteID, String preName, String surName, String discipline) {
        Discipline disciplineOfAthlete = disciplines.get(discipline);
        disciplineOfAthlete.addAthleteToDiscipline(athleteID, preName, surName);
    }

    /**
     * Methode scuht die Disziplin des Sportlers und gibt die ID weiter um zu überprüfen ob der Sportler bereits in
     * der Disziplin existiert.
     * @param athleteID ID des Athleten
     * @param discipline Disziplin
     * @return true falls es existiert, false falls nicht
     */
    public boolean athleteExistInDiscipline(int athleteID, String discipline) {
        Discipline disciplineOfAthlete = disciplines.get(discipline);
        return disciplineOfAthlete.athleteExistInDiscipline(athleteID);
    }

    /**
     * Methode sucht die gefragte Disziplin und gibt die Sportler dieser zurück
     * @param discipline Disziplin
     * @return Liste der Sportler
     */
    public String summaryAthletes(String discipline) {
        Discipline actualDiscipline = disciplines.get(discipline);
        return actualDiscipline.summaryAthletes();
    }

    /**
     * Getter für den Namen der Sportart
     * @return Name der Sportart
     */
    public String getNameOfSport() {
        return nameOfSport;
    }
}
