package edu.kit.informatik;

import edu.kit.informatik;

/**
 * Die Game Klasse bildet die Verbindung zwischen Eingabe und Bearbeitung der Eingabe.
 */
public class Game {
    private int player;
    private Gameboard board;
    private int amountOfPlayers;

    /**
     * Der Konstruktor initialisiert nach der Überprüfung die richtige Art des Spieles sowie die Spieleranzahl und der
     * erste Spieler wird festgelegt.
     * @param inputBoardType Art des Spielfeldes welches initialisiert werden soll
     * @param boardSize die größe des Spielfeldes
     * @param amountOfPlayers die Spieleranzahl
     */
    public Game(String inputBoardType, int boardSize, int amountOfPlayers) {
        if (inputBoardType.equals("torus")) {
            this.board = new TorusGameboard(boardSize);
        } else {
            this.board = new StandardGameboard(boardSize);
        }
        this.amountOfPlayers = amountOfPlayers;
        player = 1;
    }

    /**
     * Die Methode um eine Spalte des Spielbretts auszugeben. Es frägt bei dem Spielbrett die Zeile ab und leitet diese
     * weiter.
     * @param colNum Spaltennummer
     * @return Es wird ein String zurückgegeben der die gewünschte Spalte abbildet
     */
    public String colPrint(int colNum) {
        try {
            return board.getCol(colNum);
        } catch (InputException e) {
            Terminal.printError(e.getMessage());
        }
        return null;
    }

    /**
     * Methode um eine Zeile eines Spielbretts auszugeben. Es frägt bei dem Spielbrett die Zeile ab und leitet diese
     * weiter.
     * @param rowNum Zeilennummer
     * @return Es wird ein String zurückgegeben der die gewünschte Zeile abbildet
     */
    public String rowPrint(int rowNum) {
        try {
            return board.getRow(rowNum);
        } catch (InputException e) {
            Terminal.printError(e.getMessage());
        }
        return null;
    }

    /**
     * frägt bei dem Spielbrett das ganze Spiel ab und gibt dieses dann weiter
     */
    public void print() {
        board.printGame();
    }

    /**
     * Die Methode frägt den aktuellen Zustand eines Spielfeldes, an einer bestimmten Zeile und Spalte,
     * ab und gibt diesen weiter.
     * @param rowNum Zeilennummer
     * @param colNum Spaltennummer
     * @return Der abgefragte Spielstein wird zurückgegeben
     * @throws InputException bei falschen Eingaben wird eine InputException geworfen
     */
    public String state(int rowNum, int colNum) throws InputException {
        try {
            return board.getStone(rowNum, colNum);
        } catch (InputException e) {
            Terminal.printError(e.getMessage());
            return null;
        }
    }

    /**
     * Die Methode welche ein neues Spielbrett initialisieren lässt.
     * Je nachdem welches Board das aktuelle ist wird ein Torus- oder Standardspielbrett neu initialisiert, dies
     * geschieht jedoch in der Spielbrettklasse selbst.
     */
    public void reset() {
        board = board.reinitialize();
        player = 1;
    }

    /**
     * Die Methode gibt die eingegebenen Spielsteine weiter welche platziert werden sollen. Außerdem erhöht es den
     * Spieler sobald eine korrekte Eingabe getätigt wurde solange es die aktuelle Anzahl der Spieler nicht erreicht
     * hat. Ansonsten ist wieder der erste Spieler am Zug.
     * @param rowNum1 Zeilennummer des ersten zu legenden Steines
     * @param colNum1 Spaltennummer des ersten Steines
     * @param rowNum2 Zeilennummer des zweiten Steines
     * @param colNum2 Spaltennummer des dritten Steines
     */
    public void placeStone(int rowNum1, int colNum1, int rowNum2, int colNum2) {

        try {
            board.placeStone(player, rowNum1, colNum1, rowNum2, colNum2);
            if (amountOfPlayers > player) {
                player++;
            } else player = 1;
        } catch (InputException e) {
            Terminal.printError(e.getMessage());
        }
    }
}
