package edu.kit.informatik.gameboard;

/**
 * Das Standardspielbrett wird zur Abgrenzung zu einem Torus
 * Spielbrett ben�tigt. Es ist eine Kopie der Gameboard Klasse
 */
public class StandardGameboard extends Gameboard {
    /**
     * Der Konstruktor ruft den super Konstruktor
     * @param boardSize die Spielbrettgr��e
     */
    public StandardGameboard(int boardSize) {
        super(boardSize);
    }
}
