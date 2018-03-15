package edu.kit.informatik;

public class ValidateInteger {
    /**
     * Die Methode prüft eingegebene Integer Werte. Falls der Integer Wert größer als der Integer Bereich ist wird
     * ein Programm absturz verhindert, da eine Exception geworfen wird. Ansonsten wird die Zahl zurückgegeben als
     * Integer.
     * @param input Eingabewert zur Validierung
     * @return -1 bei Fehler, ansonsten den input
     */
    public static int validateInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Terminal.printError("number of input is outside the integer range");
            return -1;
        }
    }
}
