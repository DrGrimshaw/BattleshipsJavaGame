package aston.battleships;

/**
 * Created by dayachan on 20/09/2017.
 */
public abstract class AbstractPlayer implements Player {
    protected final PlayerBoard playerBoard;
    protected final EnemyBoard enemyBoard;

    public AbstractPlayer(int newBoardWidth, int newBoardHeight, int newShipsRemaining) {
        playerBoard = new PlayerBoardImpl(newBoardWidth, newBoardHeight);
        enemyBoard = new EnemyBoardImpl(newBoardWidth, newBoardHeight, newShipsRemaining);
    }

    @Override
    public CellState takeHit(Coordinates coordinates) {
        return playerBoard.takeAHit(coordinates);
    }

    @Override
    public boolean hasLost() {
        return playerBoard.areAllShipsSunk();
    }

    @Override
    public boolean hasAlreadyGuessed(Coordinates coordinates) {
        return playerBoard.hasGuessedAlready(coordinates);
    }

    @Override
    public void viewState() {
        getView().viewBoards(playerBoard, enemyBoard);
    }

    @Override
    public void updateEnemyBoard(Coordinates coordinates, CellState cellState) {
        enemyBoard.updateCellState(coordinates, cellState);
    }
}
