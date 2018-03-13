package edu.kit.informatik.input;

import edu.kit.informatik.operation.Game;
import edu.kit.informatik.operation.InputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

/**
 * Die Command Klasse nimmt die Eingaben entgegen untersucht diese und leitet sie weiter.
 */
public enum Command {


    /**
     * Überprüfung ob eine Korrekte place eingabe gemacht wurde
     */
    PLACE("place (-?\\d+);(-?\\d+);(-?\\d+);(-?\\d+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {

            if (!Math.validateInt(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4))) {
                return;
            }
            int rowNum1 = Integer.parseInt(matcher.group(1));
            int colNum1 = Integer.parseInt(matcher.group(2));
            int rowNum2 = Integer.parseInt(matcher.group(3));
            int colNum2 = Integer.parseInt(matcher.group(4));

            game.placeStone(rowNum1, colNum1, rowNum2, colNum2);
        }
    },


    /**
     * die rowprint eingabe um eine Zeile rauszubekommen
     */
    ROWPRINT("rowprint (-?\\d+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
            if (!Math.validateInt(matcher.group(1))) {
                return;
            }
            int rowNum = Integer.parseInt(matcher.group(1));

            Terminal.printLine(game.rowPrint(rowNum));
        }
    },


    /**
     * Überprüfung der colprint Eingabe um eine Spalte zurück zu bekommen
     */
    COLPRINT("colprint (-?\\d+)") {
      @Override
      public void execute(MatchResult matcher, Game game) throws InputException {
          if (!Math.validateInt(matcher.group(1))) {
              return;
          }
          int colNum = Integer.parseInt(matcher.group(1));

          Terminal.printLine(game.colPrint(colNum));
      }
    },


    /**
     * die Eingabe um das ganze Spielfeld graphisch angezeigt zu bekommen
     */
    PRINT("print") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
            game.print();
        }
    },


    /**
     * die state eingabe gibt einen bestimmten Spielstein zurück
     */
    STATE("state (-?\\d+);(-?\\d+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
            if (Math.validateInt(matcher.group(1), matcher.group(2))) {
                return;
            }
            int rowNum = Integer.parseInt(matcher.group(1));
            int colNum = Integer.parseInt(matcher.group(2));

            Terminal.printLine(game.state(rowNum, colNum));
        }
    },


    /**
     * die reset Eingabe lässt ein Spielfeld neu initialisieren
     */
    RESET("reset") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
            game.reset();
            Terminal.printLine("OK");
        }
    },


    /**
     * mit der quit Eingabe wird das Spiel abgebrochen
     */
    QUIT("quit") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
            isRunning = false;
        }
    };

    private static boolean isRunning = true;
    private Pattern pattern;

    /**
     * der Command Konstruktor nimmt eine String Eingabe entgegen und übergibt sie an das Pattern objekt
     * @param pattern entgegen genommene Eingabe
     */
    Command(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Die Methode teilt die Eingabe in Einzelteile und prüft ob sie mit einem der oberen Kommandos übereinstimmt.
     * @param input eingelesene Eingabe
     * @param game das Spiel zu dem die Eingabe gehört
     * @return gibt den passenden command zurück
     * @throws InputException falls eine falsche Eingabe vorliegt
     */
    public static Command executeMatching(String input, Game game) throws InputException {
        for (Command command : Command.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                command.execute(matcher, game);
                return command;
            }
        }
        throw new InputException("not a valid command");
    }

    /**
     * Die abstarkte Methode wird in jeder der Kommandos verwendet und überschrieben. Da sie jedes mal den gleichen
     * Kopf aber nicht Rumpf hat ist dies als abstrakte Methode gecoded.
     * @param matcher Einzelteile der Eingabe
     * @param game das Spiel auf dem die Kommandos ausgeführt werden
     * @throws InputException bei auftretenden Exception werden diese geworfen
     */
    public abstract void execute(MatchResult matcher, Game game) throws InputException;

    /**
     * Methode um herauszufinden ob das Spiel noch läuft.
     * @return ein boolean der angibt ob das Spiel läuft
     */
    public boolean isRunning() {
        return isRunning;
    }
}
