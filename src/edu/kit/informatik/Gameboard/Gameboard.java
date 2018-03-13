package edu.kit.informatik.gameboard;

import edu.kit.informatik.input.Terminal;
import edu.kit.informatik.operation.InputException;
import edu.kit.informatik.input.Math;

/**
 * Das Standardspielbrett auf dem das legen sowie Abfragen und Prüfen des Sieges abläuft.
 */
public class Gameboard {
    /**
     * Package-private, da nur im Paket die Variable weiter verwendet wird
     */
    String[][] gameBoard;
    /**
     * Package-private, da nur die erbenden Klassen die Variable verwenden
     */
    int boardSize;
    /**
     * Package-private, da die erbenden Klassen die Variable benötigen
     */
    boolean goingOn = true;

    /**
     * initialisieren eines Standardspielbretts.
     * @param boardSize die Größe des Spielbretts
     */
    public Gameboard(int boardSize) {
        this.gameBoard = new String[boardSize][boardSize];
        this.boardSize = boardSize;

        //bei der Initialisierung des Spielbrettes wird jedes Feld des zweidimensionalen Arrays mit "**" gefüllt
        for (int i = 0; i <= boardSize - 1; i++) {
            for (int j = 0; j <= boardSize - 1; j++) {
                gameBoard [i][j] = "**";
            }
        }
    }

    /**
     * Die Methode um ein Stein zu platzieren. Zuerst werden alle möglchen zu Fehlern führenden Eingaben geprüft,
     * dann die Steine gelegt und letztendlich geprüft ob damit gewonnen wurde.
     * @param player Spieler der an der Reihe ist
     * @param rowNum1 Zeilenummer erster Spielstein
     * @param colNum1 Spaltennummer erster Spielstein
     * @param rowNum2 Zeilennummer zweiter Spielstein
     * @param colNum2 Spaltennummer zweiter Spielstein
     * @throws InputException zweimal der selbe Stein, Stein außerhalb des Feldes, Feld belegt, Spiel schon vorbei
     */
    public void placeStone(int player, int rowNum1, int colNum1, int rowNum2, int colNum2) throws InputException {
        if ((rowNum1 == rowNum2) && (colNum1 == colNum2)) {
            throw new InputException("two times the same stone");
        } else if (rowNum1 >= boardSize || rowNum1 < 0 || rowNum2 >= boardSize || rowNum2 < 0 || colNum1 >= boardSize
                || colNum1 < 0 || colNum2 >= boardSize || colNum2 < 0) {
            throw new InputException("the stones you wanted to place are outside of the board");
        } else if ((!gameBoard[rowNum1][colNum1].equals("**")) || (!gameBoard[rowNum2][colNum2].equals("**"))) {
            throw new InputException("there's already something at the wanted field");
        } else if (!goingOn) {
            throw new InputException("you need to reset the board first");
        }

        gameBoard[rowNum1][colNum1] = "P" + player;
        gameBoard[rowNum2][colNum2] = "P" + player;
        if (evaluation(rowNum1, colNum1, player) || evaluation(rowNum2, colNum2, player)) {
            Terminal.printLine("P" + player + " wins");
            goingOn = false;
            return;
        }
        Terminal.printLine("OK");
    }

    /**
     * Methode welche die eingegebene Spalte als String zurück gibt. Gilt auch für Torus Spielbretter.
     * @param colNum Spaltennummer
     * @return String welcher die Spalte abbildet
     * @throws InputException falls die gewünschte Spalte nicht im Spielfeld liegt
     */
    public String getCol(int colNum) throws InputException {
        if (colNum > boardSize || colNum < 0) {
            throw new InputException("your wanted column is outside the board");
        }
        StringBuilder col = null;

        //Die gewünschte Spalte wird durch iteriert und in einen String konkatiniert, getrennt durch Leerzeichen
        for (int i = 0; i < gameBoard.length; i++) {
            if (i == 0) col = new StringBuilder((gameBoard[i][colNum] + " "));
            if (i > 0) col.append(gameBoard[i][colNum]).append(" ");
            if (i == gameBoard.length - 1) return col.toString().trim();
        }
        return null;
    }

    /**
     * Methode welche die eingegebene Zeile als String zurück gibt. Gilt genauso für Torus Spielbretter.
     * @param rowNum Zeilennummer
     * @return String welcher die Zeile abbildet
     * @throws InputException falls die gewünschte Zeile nicht im Spielfeld liegt
     */
    public String getRow(int rowNum) throws InputException {
        if (rowNum > boardSize || rowNum < 0) {
            throw new InputException("your wanted column is outside the board");
        }
        StringBuilder row = null;
        //Die gewünschte Zeile wird durch iteriert und in einen String konkatiniert, getrennt durch Leerzeichen
        for (int i = 0; i < gameBoard[rowNum].length; i++) {
            if (i == 0) row = new StringBuilder((gameBoard[rowNum][i] + " "));
            if ((i > 0) && (i < gameBoard[rowNum].length)) row.append(gameBoard[rowNum][i]).append(" ");
            if (i == gameBoard[rowNum].length - 1) return row.toString().trim();
        }
        return null;
    }

    /**
     * Methode um die belegung eines Feldes herauszubekommen
     * @param rowNum Zeilennummer
     * @param colNum Spaltennummer
     * @return String welcher den Stein abbildet
     * @throws InputException falls Zeilen oder Spaltennummer nicht auf dem Spielbrett liegt
     */
    public String getStone(int rowNum, int colNum) throws InputException {
        if (rowNum > boardSize || rowNum < 0 || colNum > boardSize || colNum < 0) {
            throw new InputException("row number or column number is outside the board");
        }
        if (gameBoard[rowNum][colNum].equals("**")) return "**";
        return gameBoard[rowNum][colNum];
    }

    /**
     * Es wird das Spielbrett ausgegebn als 2D Matrix in der Spalte durch Leerzeichen getrennt.
     */
    public void printGame() {

        // Jede Zeile wird nacheinander ausgegeben und der StringBuilder wieder für die nächste auf null gesetzt
        for (String[] aGameBoard : gameBoard) {
            StringBuilder line = null;
            for (int j = 0; j < aGameBoard.length; j++) {
                if (j == 0) line = new StringBuilder(aGameBoard[j] + " ");
                if ((j >= 1) && (j < aGameBoard.length)) line.append(aGameBoard[j]).append(" ");
                if (j == aGameBoard.length - 1) Terminal.printLine(line.toString().trim());
            }
        }
    }

    /**
     * Methode um bei einem reset Befehl das Standard Spielbrett wieder zu initialiseren. Die Run Variable wird wieder
     * auf true gesetzt um weitere Steine platzieren zu können falls das Spiel vorher beendet wurde.
     * @return das neue Standardspielbrett wird zurück gegeben
     */
    public Gameboard reinitialize() {
        goingOn = true;
        return new StandardGameboard(boardSize);
    }

    /**
     * Die Evaluations Methode ist in drei Einzelteile unterteilt. Horizontal, Vertikal und Diagonal. Diese werden
     * nacheinander abgeprüft.
     * @param row Zeile des Steins
     * @param col Spalte des Steins
     * @param player aktueller Spieler
     * @return True falls der Spieler das Spiel gewonnen hat
     */
    public boolean evaluation(int row, int col, int player) {
        return evaluateHorizontal(row, player) || evaluateVertical(col, player) || evaluateDiagonal(row, col, player);
    }

    /**
     * Die Evaluation der Diagonalen. Zuerst wird von oben links nach unten rechts in der Reihe in welcher der Stein
     * platziert wurde geprüft. Im nächsten Schritt von oben rechts nach unten links. Somit muss man von dem Stein
     * aus nicht in 4 Richtungen prüfen sondern nur die zwei Diagonalen.
     * @param row Zeile des Steins
     * @param col Spalte des Steins
     * @param player aktueller Spieler
     * @return true falls gewonnen
     */
    boolean evaluateDiagonal(int row, int col, int player) {
        /**
         * Erste Überprüfung, der startStone wird auf die Diagonale oben links an den Rand gesetzt und von dort
         * durch iteriert über den neuen Spielstein. Dabei wird der counter hochgesetzt solange Spielsteine des
         * aktuellen Spielers getroffen werden. Bei anderen Spielsteinen oder leeren Feldern wird der counter auf 0
         * gesetzt. Sobald der counter auf 6 ist bricht die Schleife ab und es kann true zurückgegeben werden
        */
        int[] startStone = Math.startForEvaluationDiagonalUpLeft(row, col, boardSize);
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (counter >= 6)
                break;
            if (counter > 0 && !gameBoard[startStone[0]][startStone[1]].equals("P" + player))
                counter = 0;
            if (gameBoard[i][i].equals("P" + player))
                counter++;
            startStone[0]++;
            startStone[1]++;
        }
        if (counter >= 6) return true;

        /**
         * Selbiges wie bei der oberen Schleife, nur dass die andere Diagonale von oben rechts nach unten links
         * geprüft wird.
         */
        counter = 0;
        startStone = Math.startForEvaluationDiagonalUpRight(row, col, boardSize);

        for (int i = 0; i < boardSize; i++) {
            if (counter >= 6 || startStone[1] == 0)
                break;
            if (counter > 0 && !gameBoard[startStone[0]][startStone[1]].equals("P" + player))
                counter = 0;
            if (gameBoard[i][i].equals("P" + player))
                counter++;
            startStone[0]++;
            startStone[1]--;
        }
        return counter >= 6;
    }

    /**
     * Evaluation der Vertikalen, es wird vom oberen Rand aus geprüft, analog danach zu der Diagonalen außer, dass
     * nur eine Richtung geprüft werden muss.
     * @param col Spaltennummer, da nur eine Spalte geprüft wird
     * @param player aktueller Spieler
     * @return true falls gewonnen
     */
    boolean evaluateVertical(int col, int player) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (counter >= 6)
                break;
            if (counter > 0 && !gameBoard[i][col].equals("P" + player))
                counter = 0;
            if (gameBoard[i][col].equals("P" + player))
                counter++;
        }
        return counter >= 6;
    }

    /**
     * Evaluation der Horizontalen, deckungsgleich zur Vertikalen außer das die Zeile gleichbleibt und somit vom linken
     * Rand aus iteriert wird.
     * @param row Zeile
     * @param player aktueller Spieler
     * @return true falls gewonnen
     */
    boolean evaluateHorizontal(int row, int player) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (counter >= 6) break;
            if (counter > 0 && !gameBoard[row][i].equals("P" + player)) counter = 0;
            if (gameBoard[row][i].equals("P" + player)) counter++;
        }
        return counter >= 6;
    }
}