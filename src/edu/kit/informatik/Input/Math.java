package edu.kit.informatik.input;


/**
 * Die Math Klasse stellt Berechnungen an und validiert Integer eingaben.
 */
public class Math {
    /**
     * Die Klasse prüft ob die eingegebenen Integer im integer Bereich liegen damit das Programm nicht abstürzt.
     * Sobald die Konvertierung von einem String zu einem int einen Fehler ergeben würde. Wird eine Exception
     * geworfen.
     * @param is beliebige Anzahl an eingegebenen integer
     * @return der return zeigt an ob die Eingabe korrekt war
     */
    public static boolean validateInt(String... is) {
        try {
            //Überprüfung der übergebenen Integer
            for (String i : is) {
                Integer.parseInt(i);
            }
        } catch (NumberFormatException e) {
            Terminal.printError("number of input is outside the integer range");
            return false;
        }
        return true;
    }

    /**
     * Da das Modulo in Java für negative Zahlen nicht die gewünschten Ergebnisse gibt muss eine spezielle Methode
     * dafür verwendet werden
     * @param number Eine Zeilen oder Spaltennummer auf die man ein Stein platzieren möchte
     * @param boardSize Die größe des Spielbrettes mit der man mod rechnet
     * @return der int gibt an, an welcher Stelle der Spielstein letztendlich platziert wird
     */
    public static int modulo(int number, int boardSize) {
        return ((number % boardSize) + boardSize) % boardSize;
    }

    /**
     * Die Methode wird verwendet zur späteren Überprüfung ob ein Spieler über die Diagonale gewonnen hat.
     * Dafür wird von dem platzierten Spielstein der obere linke Rand gesucht und zurück gegeben. Somit muss man
     * später nur in eine Richtung über die Zeile iterieren.
     * @param row Zeile des Steines
     * @param col Spalte des Steines
     * @param boardSize größe des Spielbretts
     * @return Es wird ein Array zurückgegeben welcher die Koordinate enthält ab welcher iteriert wird
     */
    public static int[] startForEvaluationDiagonalUpLeft(int row, int col, int boardSize) {
        int[] startArray = new int[2];
        for (int i = 0; i < boardSize; i++) {
            startArray[0] = row;
            startArray[1] = col;
            if (row == 0 || col == 0) return startArray;
            row--;
            col--;
        }
        return null;
    }

    /**
     * Die Methode wird verwendet zur späteren Überprüfung ob ein Spieler über die Diagonale gewonnen hat.
     * Dafür wird von dem platzierten Spielstein der obere rechte Rand gesucht und zurück gegeben. Somit muss man
     * später nur in eine Richtung über die Zeile iterieren.
     * @param row Zeile des Steines
     * @param col Spalte des Steines
     * @param boardSize größe des Spielbretts
     * @return Es wird ein Array zurückgegeben welcher die Korrdinate enthält ab welcher iteriert wird
     */
    public static int[] startForEvaluationDiagonalUpRight(int row, int col, int boardSize) {
        int[] startArray = new int[2];
        for (int i = 0; i < boardSize; i++) {
            startArray[0] = row;
            startArray[1] = col;
            if (row == 0 || col == 0 || row == boardSize || col == boardSize) return startArray;
            row--;
            col++;
        }
        return null;
    }
}
