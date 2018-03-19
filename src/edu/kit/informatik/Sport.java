package edu.kit.informatik;

import java.util.ArrayList;

/**
 * Sport Klasse mit dem Namen der Sportart sowie den dazugehörigen Disziplinen
 */
public class Sport {
    private String nameOfSport;
    private ArrayList<String> disciplines;

    /**
     * Konstruktor um eine neue Sportart zu initialisieren
     * @param nameOfSport Name des initialisierten Sportes
     */
    public Sport(String nameOfSport) {
        this.nameOfSport = nameOfSport;
        this.disciplines = new ArrayList<>();
    }

    /**
     * Hinzufügen einer Disziplin zu der Sportart
     * @param discipline Name der Sportdisziplin
     * @return false falls Disziplin bereits enthalten
     */
    public boolean addDiscipline(String discipline) {
        if (disciplines.contains(discipline)) {
            return false;
        }
        disciplines.add(discipline);
        return true;
    }

    /**
     * Getter für den Namen der Sportart
     * @return Name der Sportart
     */
    public String getNameOfSport() {
        return nameOfSport;
    }

    /**
     * Disziplinen werden in alphabetischer Reihenfolge sortiert                                                        //TODO: vllt in SportsAndDisciplinesAlphabetically Klasse verschieben (Kevin fragen)
     */
    public void sortDisciplines() {
        disciplines.sort(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * Sportart wird mit den dazugehörigen Disziplinen konkateniert und zurück gegeben
     * @return String der Konkatenation von Sportart und dazugehörigen Disziplinen in einer Liste
     */
    public String getSportAndDisciplines() {
        String sportAndDisciplines = "";
        for (String discipline : disciplines) {
            sportAndDisciplines += nameOfSport + " " + discipline + "\n";
        }
        return sportAndDisciplines;
    }
}
