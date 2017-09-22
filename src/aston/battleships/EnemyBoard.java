package aston.battleships;

public interface EnemyBoard {
    public void updateCellState(Coordinates coordinates, CellState newState);
    public int getNumberOfShipsRemaining();
    public boolean hasGuessedAlready(Coordinates coordinates);
    public CellState getCellState(Coordinates coordinates);
    public int getWidth();
    public int getHeight();
}
