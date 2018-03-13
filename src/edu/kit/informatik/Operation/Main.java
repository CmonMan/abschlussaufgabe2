package edu.kit.informatik.operation;

import edu.kit.informatik.input.Command;
import edu.kit.informatik.input.Terminal;

/**
 * Die Main Klasse nimmt die Parameter entgegen testet diese und ist die Klasse, in welcher das Spiel ausgeführt wird.
 */
public class Main {
    /**
     * Die Main methode initialisiert das aus den Startparametern eingegangene Spielbrett und nimmt Eingaben der Spieler
     * entgegen und gibt diese weiter. Bei falscher Startparameter eingabe bricht die main-methode ab und das Spiel
     * muss von neuem gestartet werden.
     * @param args Startparameter die entgegen genommen werden
     */
    public static void main(String[] args) {
        if (!argumentsCorrect(args)) {
            return;
        }
        Command command = null;
        Game connectSix = new Game(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        // die do-while Schleife lässt das Spiel laufen bis eine Abbruchseingabe erfolgt
        do {
            try {
                command = Command.executeMatching(Terminal.readLine(), connectSix);
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || command.isRunning());

    }

    /**
     * Die Methode argumentsCorrect testet die Startparameter auf Gültigkeit
     * @param args die Startparameter aus der Main-methode werden angenommen
     * @return bei richtiger Eingabe wird true zurückgegeben ansonsten false und das Programm wird beendet
     */
    private static boolean argumentsCorrect(String[] args) {

        if (!(args.length == 3)) {
            Terminal.printError("wrong start command, not more or less than 3 arguments allowed");
            return false;
        }
        if ((!args[0].equals("torus")) && ((!args[0].equals("standard")))) {
            Terminal.printError("the game mode must be torus or standard");
            return false;
        }
        try {
            int numberOfPlayers = Integer.parseInt(args[2]);
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                Terminal.printError("the number of players must be between 2 and 4");
                return false;
            }
            int boardSize = Integer.parseInt(args[1]);
            if (!(boardSize == 18 || boardSize == 20)) {
                Terminal.printError("the board size must be 18 or 20");
                return false;
            }
        } catch (NumberFormatException e) {
            Terminal.printError("the number of your input is above the integer limit and not valid");
            return false;
        }
        return true;
    }


}
