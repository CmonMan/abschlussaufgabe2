package edu.kit.informatik;

import java.util.ArrayList;

/**
 * Klasse stellt einen Athleten dar. Es wird außerdem gespeichert wie viele Medaillen und in welchen Jahren der
 * Sportler diese gewonnen hat.
 */
public class Athlete extends Person implements Comparable<Athlete> {

    private String id;
    private int medal;
    private ArrayList<Integer> yearsOfCompetitionMedal;

    /**
     * Konstruktor für Athlete Klasse
     * @param athleteID ID des Athleten
     * @param preName Vorname
     * @param surName Nachname
     */
    public Athlete(int athleteID, String preName, String surName) {
        yearsOfCompetitionMedal = new ArrayList<>();
        this.id = Integer.toString(athleteID);
        this.preName = preName;
        this.surName = surName;
        this.medal = 0;
    }


    /**
     * Um ein Wettkampf Ergebnis hinzuzufügen
     * @param medal Medaille
     * @param year Jahr des Wettkampfes
     */
    public void setWonCompetition(int medal, int year) {
        this.medal += medal;
        yearsOfCompetitionMedal.add(year);
    }

    /**
     * Methode überprüft ob der Athlet in dem Jahr schon gewonnen hat
     * @param year Jahr
     * @return True falls gewonnen in dem Jahr, false falls nicht
     */
    public boolean testCompetitionYear(int year) {
        for (Integer wonCompetitionYear : yearsOfCompetitionMedal) {
            if (wonCompetitionYear == year ) return true;
        }
        return false;
    }

    /**
     * Methode um zwei Athlete nach der Anzahl ihrer Medaillen in der Sportart sowie ihrer ID zu vergleichen.
     * @param other der Athlet mit dem verglichen wird
     * @return 1 falls Athlet mehr Medaillen, -1 wenn nicht,  dritter return für nächste Vergleichsbedingung
     */
    @Override
    public int compareTo(Athlete other) {
        if (this.medal > other.getMedal()) {
            return 1;
        } else if (this.medal > other.getMedal()) {
            return -1;
        } else return this.id.compareTo(other.getId());
    }

    /**
     * Getter für die ID
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Getter für die Anzahl der Medaillen des Sportlers in der Sportart
     * @return Anzahl Medaillen
     */
    public int getMedal() {
        return medal;
    }

    /**
     * Getter für den Vornamen
     * @return Vorname
     */
    public String getPreName() {
        return preName;
    }

    /**
     * Getter für den Nachnamen
     * @return Nachname
     */
    public String getSurName() {
        return surName;
    }
}
