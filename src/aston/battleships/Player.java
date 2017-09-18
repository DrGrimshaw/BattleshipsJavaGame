package aston.battleships;

public interface Player {
    public class ResignedException extends Exception {

    }

    public void placeShipOnToPlayerBoard(int lengthOfShip);
    public Coordinates chooseMove() throws ResignedException;
    public CellState takeHit(Coordinates coordinates);
    public boolean isGameOver();
    public boolean hasAlreadyGuessed(Coordinates coordinates);
    public void viewState();
}
