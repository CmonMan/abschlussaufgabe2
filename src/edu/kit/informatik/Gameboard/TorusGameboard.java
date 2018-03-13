package edu.kit.informatik.gameboard;

import edu.kit.informatik.input.Math;
import edu.kit.informatik.input.Terminal;
import edu.kit.informatik.operation.InputException;

/**
 * Klasse für den Torus Spielmodus.
 */
public class TorusGameboard extends Gameboard {
    /**
     * Der Konstruktor übergibt dem Superkonstruktor die Spielbrettgröße
     * @param boardSize Spielbrettgröße
     */
    public TorusGameboard(int boardSize) {
        super(boardSize);
    }

    /**
     * Methode zum platzieren der Steine auf einem Torus Spielbrett. Erst wird der endgültige Ablageort des Steines
     * mithilfe von Math.modulo herausgefunden und daraufhin geprüft ob das Spielfeld belegt ist oder die Steine den
     * selben letztendlichen Wert haben. Die Steine werden dann platziert und es wird geprüft ob der Spieler mit diesen
     * Steinen gewonnen hat.
     * @param player aktueller Spieler
     * @param rowNum1 Zeilenummer des ersten Steins
     * @param colNum1 Spaltennummer erster Stein
     * @param rowNum2 Zeilennummer zweiter Stein
     * @param colNum2 Spaltennummer zweiter Stein
     * @throws InputException falls zwei mal der selbe Stein nach modulo Rechnung eingegeben wurde, falls belegt
     */
    @Override
    public void placeStone(int player, int rowNum1, int colNum1, int rowNum2, int colNum2) throws InputException {
        rowNum1 = Math.modulo(rowNum1, boardSize);
        colNum1 = Math.modulo(colNum1, boardSize);
        rowNum2 = Math.modulo(rowNum2, boardSize);
        colNum2 = Math.modulo(colNum2, boardSize);

        if ((rowNum1 == rowNum2) && (colNum1 == colNum2)) {
            throw new InputException("two times the same stone");
        } else if ((!gameBoard[rowNum1][colNum1].equals("**")) || (!gameBoard[rowNum2][colNum2].equals("**"))) {
            throw new InputException("there's already something at the wanted field");
        } else if (!goingOn) {
            throw new InputException("you need to reinitialize the board");
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
     * Methode um ein Spielbrett neu zu initialisieren. Die goingOn Variable wird true gesetzt damit Spielsteine
     * wieder gesetzt werden können
     * @return neues Torus Spielbrett wird zurück gegeben
     */
    @Override
    public Gameboard reinitialize() {
        goingOn = true;
        return new TorusGameboard(boardSize);
    }

    /**
     * Methode um die Belegung eines gefragten Spielfeldes zu bekommen. Zuerst mit Math.modulo den endgültigen Ort
     * des Spielsteins bekommen und danach die Abfrage der Belegung des Feldes.
     * @param rowNum Zeilennummer
     * @param colNum Spaltennummer
     * @return String mit der Abbildung des Feldes
     */
    @Override
    public String getStone(int rowNum, int colNum) {
        rowNum = Math.modulo(rowNum, boardSize);
        colNum = Math.modulo(colNum, boardSize);

        if (gameBoard[rowNum][colNum].equals("**")) return "**";
        return gameBoard[rowNum][colNum];
    }

    /**
     * Dreigeteilte Evaluation der Gewinnanalyse. Horizontal, Vertikal und Diagonal müssen geprüft werden.
     * @param row Zeile des gesetzten Steines
     * @param col Spalte des gesetzten Steines
     * @param player aktueller Spieler
     * @return true falls der Spieler gewonnen hat
     */
    @Override
    public boolean evaluation(int row, int col, int player) {
        return evaluateHorizontal(row, player) || evaluateVertical(col, player) || evaluateDiagonal(row, col, player);
    }

    /**
     * Gewinnanalyse der Diagonalen. Zuerst mit super analog zu einem normalen Spielbrett. Danach werden die
     * Diagonalen auf überlauf getestet.
     * @param row Zeilennummer
     * @param col Spaltennummer
     * @param player aktueller Spieler
     * @return true falls über Diagonale gewonnen
     */
    @Override
    boolean evaluateDiagonal(int row, int col, int player) {

        if (super.evaluateDiagonal(row, col , player))
            return true;

        // falls das obere linke sowie untere rechte Feld nicht belegt ist muss nicht weiter geprüft werden
        if (!(row == col) && !gameBoard[0][0].equals("P" + player)
                && !gameBoard[boardSize - 1][boardSize - 1].equals("P" + player))
            return false;

        int counter = 0;
        int i = 0;
        int dir = 1;

        /**
         * Erste Hauptdiagonale die auf überlauf geprüft wird (Oben,Links / Unten,Rechts)
         * Zuerst wird von dem oberen linken Feld iteriert solange die Steine dem aktuellen Spieler entsprechen. Dies
         * wird mit Hilfe des counters gezählt. Falls ein Feld erreicht wird, dass nicht den Steinen des Spielers
         * etnspricht wird auf die andere Seite (Unten,Rechts) gesprungen un der counter wieder so lange erhöht, bis
         * ein Feld nicht mehr dem Spieler entspricht. Ist der counter >= 6 wird true zurück gegeben und die
         * Evaluation ist zu Ende.
         */
        while (true) {
            if (!gameBoard[i][i].equals("P" + player)) {
                if (dir == -1)
                    break;

                i = boardSize - 1;
                dir = -1;

            } else {
                counter++;
                i += dir;

                if (counter > 5)
                    return true;
                else if (i <= 0)
                    break;
            }
        }

        // falls das Feld oben rechts nicht belegt ist muss nicht weiter geprüft werden
        if (!(((row + col) / 2) == boardSize) && !gameBoard[boardSize - 1][0].equals("P" + player)
                && !gameBoard[0][boardSize - 1].equals("P" + player))
            return false;

        counter = 0;
        i = boardSize - 1;
        int j = 0;
        dir = 1;

        //Analog zu der anderen Diagonalen
        while (true) {
            if (!gameBoard[i][j].equals("P" + player)) {
                if (dir == -1)
                    return false;

                i = 0;
                j = boardSize - 1;

                dir = -1;
            } else {

                counter++;
                i -= dir;
                j += dir;

                if (counter > 5)
                    return true;
                else if (i < boardSize - 1)
                    return false;
            }
        }

        }

    /**
     * Überpüfung der Vertikalen auf einen Sieg. Wie bei der Diagonalen wird erst die Methode des Standard
     * Spiebrettes verwendet und dann auf überlauf analog zur Diagonalen geprüft. Die Spalte bleibt gleicht und nur
     * über die Zeilen wird iteriert.
     * @param col Spalte des Steines
     * @param player aktueller Spieler
     * @return true falls Gewinn über die Vertikale
     */
    @Override
    boolean evaluateVertical(int col, int player) {
        if (super.evaluateVertical(col, player))
            return true;

        if (!gameBoard[0][col].equals("P" + player))
            return false;

        int counter = 0;
        int i = 0;
        int dir = 1;

        while (true) {
            if (!gameBoard[i][col].equals("P" + player)) {
                if (dir == -1)
                    return false;

                i = boardSize - 1;
                dir = -1;
            } else {
                counter++;
                i += dir;

                if (counter > 5)
                    return true;
                else if (i <= 0)
                    return false;
            }
        }
    }

    /**
     * Überpüfung der Horizontalen auf einen Sieg. Wie bei der Diagonalen wird erst die Methode des Standard
     * Spiebrettes verwendet und dann auf überlauf analog zur Diagonalen geprüft. Die Zeile bleibt gleicht und nur
     * über die Spalten wird iteriert.
     * @param row Zeile des Steines
     * @param player aktueller Spieler
     * @return true falls Gewinn über die Horizontale
     */
    @Override
    boolean evaluateHorizontal(int row, int player) {
        if (super.evaluateHorizontal(row, player))
            return true;

        if (!gameBoard[row][0].equals("P" + player))
            return false;

        int counter = 0;
        int i = 0;
        int dir = 1;

        while (true) {
            if (!gameBoard[row][i].equals("P" + player)) {
                if (dir == -1)
                    return false;

                i = boardSize - 1;
                dir = -1;

            } else {
                counter++;
                i += dir;

                if (counter > 5)
                    return true;
                else if (i <= 0)
                    return false;
            }
        }
    }

}