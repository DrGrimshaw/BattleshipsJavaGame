package aston.battleships;

import java.util.List;
public interface PlayerBoard {
    public int getWidth();
    public int getHeight();
    public void placeShip(Ship ship);
    //TODO: get cell state
    public boolean hasGuessedAlready(int x, int y);
    public Ship getShip(int x, int y);    
    public int getNumberOfShipsRemaining();
    public List<Ship> getShips();
    public boolean isGameOver();
}
