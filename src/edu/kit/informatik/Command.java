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
    ADD_ADMIN("add-admin ([^;\\n]+);([^;\\n]+);([^;\\n]{4,8});([^;\\n]{8,12})") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            String preName = matcher.group(1);
            String surName = matcher.group(2);
            String userName = matcher.group(3);
            String password = matcher.group(4);

            olympicWinterGames.addAdmin(preName, surName, userName, password);
            Terminal.printLine("OK");
        }
    },
    /**
     * Anmeldung eines Admins mithilfe von Benutzernamen und Passwort.
     */
    LOGIN_ADMIN("login-admin ([^\\n;]+);([^\\n;]+)") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            String userName = matcher.group(1);
            String password = matcher.group(2);

            olympicWinterGames.loginAdmin(userName, password);
            Terminal.printLine("OK");
        }
    },
    /**
     * Abmeldung des aktuell angemeldeten Admins.
     */
    LOGOUT_ADMIN("logout-admin") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            olympicWinterGames.logoutAdmin();
            Terminal.printLine("OK");
        }
    },
    /**
     * Hinzüfungen einer Sportstätte zum System.
     */
    ADD_SPORTS_VENUE ("add-sports-venue (\\d{3});([^\\d\\W]+);([^\\d\\W]+);([^\\d\\W]+);(\\d{4});(\\d+)") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            String sportsVenueID = matcher.group(1);
            String countryName = matcher.group((2));
            String place = matcher.group(3);
            String sportsVenueName = matcher.group(4);
            int yearOfOpening = ValidateInteger.validateInt(matcher.group(5));
            int amountOfSeats = ValidateInteger.validateInt(matcher.group(6));
            if(yearOfOpening == -1 || amountOfSeats == -1) return;
            olympicWinterGames.addSportsVenue(sportsVenueID, countryName, place, sportsVenueName, yearOfOpening,
                    amountOfSeats);
            Terminal.printLine("OK");
        }
    },
    /**
     * Ausgabe der Sportstätten nach Anzahl der Sitzplätze
     */
    LIST_SPORTS_VENUES("list-sports-venues ([^\\d\\W]+)") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            String countryName = matcher.group(1);

            Terminal.printLine(olympicWinterGames.listSportsVenues(countryName));
        }
    },
    /**
     * Hinzüfugen einer Olympischen Sportart, sowie Sportdisziplin
     */
    ADD_OLYMPIC_SPORT("add-olympic-sport ([^\\n;]+);([^\\n;]+)") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            String sport = matcher.group(1);
            String discipline = matcher.group(2);

            olympicWinterGames.addOlympicSport(sport, discipline);
            Terminal.printLine("OK");
        }
    },
    /**
     * Ausgabe der Sportarten und Disziplinen alphabetisch gelistet
     */
    LIST_OLYMPIC_SPORTS("list-olympic-sports") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            Terminal.printLine(olympicWinterGames.listSportsAndDisciplines());
        }
    },
    /**
     * Hinzüfügen eines IOC-Länder-Codes
     */
    ADD_IOC_CODE("add-ioc-code (\\d{3});([a-z]{3});([^\\d\\W]+);(\\d{4})") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            int iocID = ValidateInteger.validateInt(matcher.group(1));
            String iocCode = matcher.group(2);
            String countryName = matcher.group(3);
            int year = ValidateInteger.validateInt(matcher.group(4));

            olympicWinterGames.addIOCCode(iocID, iocCode, countryName, year);
            Terminal.printLine("OK");
        }
    },
    /**
     * Ausgabe der IOC-Länder Codes aufsteigend nach Festlegungsjahr
     */
    LIST_IOC_CODE("list-ioc-codes") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            Terminal.printLine(olympicWinterGames.listIOCCodes());
        }
    },
    /**
     * Hinzufügen eines Sportlers
     */
    ADD_ATHLETE("add-athlete (\\d{4});([^\\n;]+);([^\\n;]+);([^\\n;]+);([\\w\\D]+);([\\w\\D]+)") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            int athleteID = ValidateInteger.validateInt(matcher.group(1));
            String preName = matcher.group(2);
            String surName = matcher.group(3);
            String countryName = matcher.group(4);
            String sport = matcher.group(5);
            String discipline = matcher.group(6);

            olympicWinterGames.addAthlete(athleteID, preName, surName, countryName, sport, discipline);
            Terminal.printLine("OK");
        }
    },
    /**
     * Ausgabe von Sportlern anhand des Erfolges in ihrem Sport bzw. ihrer Disziplin
     */
    SUMMARY_ATHLETES("summary-athletes ([\\w\\D]+);([\\w\\D]+)") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            String sport = matcher.group(1);
            String discipline = matcher.group(2);

            Terminal.printLine(olympicWinterGames.summaryAthletes(sport, discipline));
        }
    },
    /**
     * Hinzufügen eines Olympischen Wettkampfes
     */
    ADD_COMPETITION("add-competition (\\d{4});(\\d{4});([^\\n;]+);([^\\n;]+);([^\\n;]+);([0,1]);([0,1]);([0,1])") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            int athleteID = ValidateInteger.validateInt(matcher.group(1));
            int year = ValidateInteger.validateInt(matcher.group(2));
            String countryName = matcher.group(3);
            String sport = matcher.group(4);
            String discipline = matcher.group(5);
            int gold = Integer.parseInt(matcher.group(6));
            int silver = Integer.parseInt(matcher.group(7));
            int bronze = Integer.parseInt(matcher.group(8));

            olympicWinterGames.addCompetition(athleteID, year, countryName, sport, discipline, gold, silver, bronze);
            Terminal.printLine("OK");
        }
    },
    /**
     * Ausgabe des ewigen Medaillenspiegel
     */
    OLYMPIC_MEDAL_TABLE("olympic-medal-table") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            Terminal.printLine(olympicWinterGames.listOlympicMedalTable());
        }
    },
    /**
     * Der Reset Befehl initialisiert die Olympischen Spiele neu. Es bleiben aber alle registrierten Benutzer erhalten.
     */
    RESET("reset") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
            olympicWinterGames.resetTheWinterGames();
            Terminal.printLine("OK");
        }
    },
    /**
     * Der Quit Befehl bringt das Programm zum stoppen
     */
    QUIT("quit") {
        @Override
        public void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException {
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
    //TODO: richtiger Kommentar für Verwaltungssystem und nicht connectSix
    /**
     * Die Methode teilt die Eingabe in Einzelteile und prüft ob sie mit einem der oberen Kommandos übereinstimmt.
     * @param input eingelesene Eingabe
     * @param olympicWinterGames das Spiel zu dem die Eingabe gehört
     * @return gibt den passenden command zurück
     * @throws InputException falls eine falsche Eingabe vorliegt
     */
    public static Command executeMatching(String input, Administration olympicWinterGames) throws InputException {
        for (Command command : Command.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                command.execute(matcher, olympicWinterGames);
                return command;
            }
        }
        throw new InputException("not a valid command");
    }

    /**
     * Die abstarkte Methode wird in jeder der Kommandos verwendet und überschrieben. Da sie jedes mal den gleichen
     * Kopf aber nicht Rumpf hat ist dies als abstrakte Methode gecoded.
     * @param matcher Einzelteile der Eingabe
     * @param olympicWinterGames das Spiel auf dem die Kommandos ausgeführt werden
     * @throws InputException bei auftretenden Exception werden diese geworfen
     */
    public abstract void execute(MatchResult matcher, Administration olympicWinterGames) throws InputException;

    /**
     * Methode um herauszufinden ob das Spiel noch läuft.
     * @return ein boolean der angibt ob das Spiel läuft
     */
    public boolean isRunning() {
        return isRunning;
    }
}
