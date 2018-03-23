package edu.kit.informatik;

/**
 * Main Klasse nimmt Eingabe entgegen und looped das Programm bis zur quit eingabe
 */
public class Main {
    /**
     * Main Methode
     * @param args Startparameter
     */
    public static void main(String[] args) {
        Administration olympicWinterGamesAdministration = new Administration();
        Command command = null;

        do {
            try {
                command = Command.executeMatching(Terminal.readLine(), olympicWinterGamesAdministration);
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || command.isRunning());
    }
}
