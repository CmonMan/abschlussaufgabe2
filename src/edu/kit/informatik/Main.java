package edu.kit.informatik;

public class Main {
    public static void main(String[] args) {
        Administration olympicWinterGamesAdministration = new Administration();
        Command command = null;

        do {
            try {
                command = Command.executeMatching(Terminal.readLine(), olympicWinterGamesAdministration);
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        }while (command == null || command.isRunning());
    }
}
