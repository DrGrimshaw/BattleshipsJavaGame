package aston.battleships;

import java.util.List;

public interface PlayerBoard {
    class ShipOverlapException extends Exception {}

    public int getWidth();
    public int getHeight();
    public void placeShip(Ship ship) throws ShipOverlapException;
    public CellState getCellState(Coordinates coordinates);
    public boolean hasGuessedAlready(Coordinates coordinates);
    public Ship getShip(Coordinates coordinates);
    public int getNumberOfShipsRemaining();
    public List<Ship> getShips();
    public boolean areAllShipsSunk();
    public CellState takeHit(Coordinates coordinates);
}
