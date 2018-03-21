package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ListOfCountriesAndIOCCodesBySettingYear {

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
        for (IOC ioc : listOfIOCCodes) {
            iocList += ioc.getYear() + " " + ioc.getIocID() + " " + ioc.getIocCode() +
                    " ";
            for(Map.Entry<String, Country> entry : countries.entrySet()) {
                if (entry.getValue().getIOC().getIocID() == ioc.getIocID()) {
                    iocList += entry.getValue().getCountryName() + "\n";
                }
            }
        }
        return iocList.trim();                                                                                          //TODO:kommentieren
    }
}
