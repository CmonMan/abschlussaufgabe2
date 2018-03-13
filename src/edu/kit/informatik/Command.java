package edu.kit.informatik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

/**
 * Die Command Klasse nimmt die Eingaben entgegen untersucht diese und leitet sie weiter.
 */
public enum Command {
    ADD_ADMIN("add-admin (\\wD);(\\wD);(\\w);(\\w)") { //TODO:richtiger Regex Befehl für String ohne digits
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    LOGIN_ADMIN("login-admin (\\w);(\\w)") { //TODO: überprüfen ob regex richtig für Fragestellung/ zweiter regex falsch
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    LOGOUT_ADMIN("logout-admin ();();();();();(\\d+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    ADD_SPORTS_VENUE ("add-sports-venue ();();();();();()") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    LIST_SPORTS_VENUE("list-sports-venue (\\w)") { //TODO: Ländernahme string ohne Zahlen
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    ADD_OLYMPIC_SPORT("add-olympic-sport ();()") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    LIST_OLYMPIC_SPORTS("add-olympic-sport ") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    ADD_IOC_CODE("add-ioc-code ();();();()") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    LIST_IOC_CODE("list-ioc-code ") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    ADD_ATHLETE("add-athlete ();();();();();()") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    SUMMARY_ATHLETES("summary-athletes ();()") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    ADD_COMPETITION("add-competition ();();();();();();();()") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    OLYMPIC_MEDAL_TABLE("olympic-medal-table ") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
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

}
