package edu.kit.informatik;

/**
 * Die Analysis Klasse stellt verschiedenste Evaluationen, von Integer validierung bis sortierung von Zahlen sowie
 * Wörtern an.
 */
public class Analysis {

    /**
     * Die Methode prüft eingegebene Integer Werte. Falls der Integer Wert größer als der Integer Bereich ist wird
     * ein Programm absturz verhindert, da eine Exception geworfen wird. Ansonsten wird die Zahl zurückgegeben als
     * Integer.
     * @param input Eingabewert zur Validierung
     * @return -1 bei Fehler, ansonsten den input
     */
    public static int validateInt(String input) {
        try {
            int output = Integer.parseInt(input);
            return output;
        } catch (NumberFormatException e) {
            Terminal.printError("number of input is outside the integer range");
            return -1;
        }
    }
}
