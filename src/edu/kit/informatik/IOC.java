package edu.kit.informatik;

/**
 * IOC Code der zu einem Land zugehörig ist
 */
public class IOC implements Comparable<IOC>{
    private int iocID;
    private String iocCode;
    private int year;

    /**
     * IOC Objekt Konstruktor
     * @param iocID IOC ID
     * @param iocCode IOC Code
     * @param year Festlegungsjahr
     */
    public IOC(int iocID, String iocCode, int year) {
        this.iocCode = iocCode;
        this.iocID = iocID;
        this.year = year;
    }

    /**
     * Festlegungsjahr Getter
     * @return Festlegungsjahr
     */
    public int getYear() {
        return year;
    }

    /**
     * IOC ID Getter
     * @return IOC ID
     */
    public int getIocID() {
        return iocID;
    }

    /**
     * IOC Code Getter
     * @return IOC Code
     */
    public String getIocCode() {
        return iocCode;
    }

    /**
     * Methode überschreibt die compareTo Methode der Comparator Klasse
     * @param other anderer IOC Code
     * @return die Reihenfolge der vergleichten IOC codes
     */
    @Override
    public int compareTo(IOC other) {
        Integer settingYear1 = getYear();
        Integer settingYear2 = other.getYear();
        int sComp = settingYear1.compareTo(settingYear2);

        if (sComp != 0) {
            return sComp;
        } else {
            Integer id1 = getIocID();
            Integer id2 = other.getIocID();
            return id1.compareTo(id2);
        }
    }
}
