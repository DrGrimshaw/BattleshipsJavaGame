package aston.battleships;

import java.io.IOException;

public interface Player {
    public class ResignedException extends Exception {

    }

    public void placeShipOnToPlayerBoard(int lengthOfShip);
    public Coordinates chooseMove() throws ResignedException;
    public CellState takeHit(Coordinates coordinates);
    public boolean hasLost();
    public boolean hasAlreadyGuessed(Coordinates coordinates);
    public void viewState();
    public void updateEnemyBoard(Coordinates coordinates, CellState cellState);
}
