package edu.kit.informatik;

public class IOC implements Comparable<IOC>{
    private int iocID;
    private String iocCode;
    private int year;

    public IOC(int iocID, String iocCode, int year) {
        this.iocCode = iocCode;
        this.iocID = iocID;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getIocID() {
        return iocID;
    }

    public String getIocCode() {
        return iocCode;
    }

    /**
     * @param other
     * @return
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
