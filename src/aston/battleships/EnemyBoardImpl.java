package aston.battleships;

/**
 * Created by lawtonel on 18/09/2017.
 */
public class EnemyBoardImpl implements EnemyBoard {
    private final CellState[][] grid;
    private final int width, height;
    private int shipsRemaining;

    public EnemyBoardImpl(int newWidth, int newHeight, int newShipsRemaining) {
        if(newWidth <= 0 || newHeight <=0) {
            throw new IllegalArgumentException(newHeight + " by " + newWidth + " is not a legal board size");
        }
        if(newShipsRemaining <= 0) {
            throw new IllegalArgumentException("Can not start the game as the number of ships remaining is " + newShipsRemaining);
        }

        width = newWidth;
        height = newHeight;
        shipsRemaining = newShipsRemaining;

        grid = new CellState[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                grid[x][y] = CellState.NOTHING;
            }
        }
    }

    @Override
    public void updateCellState(Coordinates coordinates, CellState newState) {
        coordinates.checkBounds(width, height);
        if(newState == null) {
            throw new IllegalArgumentException("New cell state was null.");
        } else if(newState == CellState.SHIP_SUNK || newState == CellState.SHIP_NOT_HIT) {
            throw new IllegalArgumentException(newState + " is not a valid cell state for enemy board.");
        } else {
            grid[coordinates.x][coordinates.y] = newState;
        }
    }

    @Override
    public int getNumberOfShipsRemaining() {
        return shipsRemaining;
    }

    @Override
    public boolean hasGuessed(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        return grid[coordinates.x][coordinates.y] != CellState.NOTHING;
    }

    @Override
    public CellState getCellState(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        return grid[coordinates.x][coordinates.y];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
