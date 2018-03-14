package edu.kit.informatik;

/**
 * Die InputException Klasse behandelt falsche Eingaben. Sie erbt von Exception.
 */
public class InputException extends Exception {
    /**
     * Der Konstruktor nimmt eine Fehlermeldung entgegen und gibt diese zurück mit einem "Error, " vornedran
     * @param errorMessage die aussagekräftige Fehlermeldung wird entgegen genommen
     */
    public InputException(String errorMessage) {
        super(errorMessage);
    }
}