package edu.kit.informatik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

/**
 * Die Command Klasse nimmt die Eingaben entgegen untersucht diese und leitet sie weiter.
 */
public enum Command {
    /**
     * Befehl zum hinzufügen eines Admin. Hierfür darf kein Admin angemeldet sein.
     */
    ADD_ADMIN("add-admin ([a-zA-Z]+);([a-zA-Z]+);([^;\r\n]+{4,8});([^;\r\n]{8,12})") { //TODO:Regex ob mehr angenommen werden soll als nur Buchstaben, nicht semikolon und zeilenumbruch
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Anmeldung eines Admins mithilfe von Benutzernamen und Passwort.
     */
    LOGIN_ADMIN("login-admin ([^;\n\r]+);([^\n\r]+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Abmeldung des aktuell angemeldeten Admins.
     */
    LOGOUT_ADMIN("logout-admin") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Hinzüfungen einer Sportstätte zum System.
     */
    ADD_SPORTS_VENUE ("add-sports-venue (\\d{3});(\\w+);(\\w+);(\\w+);([1,2]\\d{3});(\\d+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Ausgabe der Sportstätten nach Anzahl der Sitzplätze
     */
    LIST_SPORTS_VENUE("list-sports-venue ([a-zA-Z]+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Hinzüfugen einer Olympischen Sportart, sowie Sportdisziplin
     */
    ADD_OLYMPIC_SPORT("add-olympic-sport ([^;\r\n]);([^;\r\n])") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Ausgabe der Sporstätten alphabetisch gelistet
     */
    LIST_OLYMPIC_SPORTS("list-olympic-sport") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Hinzüfügen eines IOC-Länder-Codes
     */
    ADD_IOC_CODE("add-ioc-code (\\d{3});([a-z]{3});([a-zA-Z]+);([1,2]\\d{3})") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Ausgabe der IOC-Länder Codes aufsteigend nach Festlegungsjahr
     */
    LIST_IOC_CODE("list-ioc-code") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Hinzufügen eines Sportlers
     */
    ADD_ATHLETE("add-athlete (\\d{4});([a-zA-Z]+);([a-zA-Z]+);([^;\r\n]+);([a-zA-Z]+);([a-zA-Z]+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Ausgabe von Sportlern anhand des Erfolges in ihrem Sport bzw. ihrer Disziplin
     */
    SUMMARY_ATHLETES("summary-athletes ([a-zA-Z]+);([a-zA-Z]+)") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Hinzufügen eines Olympischen Wettkampfes
     */
    ADD_COMPETITION("add-competition (\\d{4});([1,2]\\d{3});([a-zA-Z]+);([a-zA-Z]+);([a-zA-Z]+);([0,1]);([0,1]);([0,1])") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Ausgabe des ewigen Medaillenspiegel
     */
    OLYMPIC_MEDAL_TABLE("olympic-medal-table") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {
        }
    },
    /**
     * Der Reset Befehl initialisiert die Olympischen Spiele neu. Es bleiben aber alle registrierten Benutzer erhalten.
     */
    RESET("reset") {
        @Override
        public void execute(MatchResult matcher, Game game) throws InputException {

        }
    },
    /**
     * Der Quit Befehl bringt das Programm zum stoppen
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
