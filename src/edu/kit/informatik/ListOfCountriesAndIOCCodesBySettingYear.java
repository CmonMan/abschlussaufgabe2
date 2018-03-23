package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Die Klasse sortiert mithilfe der static Methode die Länder nach Festlegungsjahr des IOC Codes.
 */
public class ListOfCountriesAndIOCCodesBySettingYear {

    /**
     * Methode um einen String der Länder sortiert nach Festlegungsjahr des IOC Codes zu bekommen
     * @param countries die zu prüfenden Länder
     * @return Die Liste der Länder
     */
    public static String listCountriesAndIOC(Map<String, Country> countries) {
        ArrayList<IOC> listOfIOCCodes = new ArrayList<>();

        // Arrayliste wird mit IOC Codes von Ländern gefüllt
        for (String countryName : countries.keySet()) {
                listOfIOCCodes.add(countries.get(countryName).getIOC());
        }
        if (listOfIOCCodes.isEmpty()) {
            return null;
        }
        Collections.sort(listOfIOCCodes);

        String iocList = "";
        // String Konkatentation der Länder nach Festlegunsjahr der IOC Codes
        for (IOC ioc : listOfIOCCodes) {
            iocList += ioc.getYear() + " " + ioc.getIocID() + " " + ioc.getIocCode() +
                    " ";
            /*
             Um den Namen des Landes nicht noch einmal in der IOC Klasse zu speichern wird hier geprüft zu welchem
             Land der IOC code gehört und dessen Name abgefragt und ausgegeben
              */
            for(Map.Entry<String, Country> entry : countries.entrySet()) {
                if (entry.getValue().getIOC().getIocID() == ioc.getIocID()) {
                    iocList += entry.getValue().getCountryName() + "\n";
                }
            }
        }
        return iocList.trim();
    }
}
