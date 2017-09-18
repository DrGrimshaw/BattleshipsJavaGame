package aston.battleships;

public interface EnemyBoard {
    public void updateCellState(Coordinates coordinates);
    public int getNumberOfShipsRemaining();
    public boolean hasGuessed(Coordinates coordinates);
    public CellState getCellState(Coordinates coordinates);
    public int getWidth();
    public int getHeight();
}
