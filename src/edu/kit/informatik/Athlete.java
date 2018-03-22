package edu.kit.informatik;

public class Athlete extends Person implements Comparable<Athlete>{

    private String id;
    private int medal;

    public Athlete(int athleteID, String preName, String surName) {
        this.id = Integer.toString(athleteID);
        this.preName = preName;
        this.surName = surName;
        this.medal = 0;
    }


    public void setMedal(int medal) {
        this.medal = medal;
    }

    /**
     * Methode um zwei Athlete nach der Anzahl ihrer Medaillen in der Sportart sowie ihrer ID zu vergleichen.
     * @param other der Athlet mit dem verglichen wird
     * @return 1 falls Athlet mehr Medaillen, -1 wenn nicht,  dritter return für nächste Vergleichsbedingung
     */
    @Override
    public int compareTo(Athlete other) {
        if(this.medal > other.getMedal()) {
            return 1;
        } else if(this.medal > other.getMedal()) {
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
