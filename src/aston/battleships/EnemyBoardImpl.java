package aston.battleships;

/**
 * Created by lawtonel on 18/09/2017.
 */
public class EnemyBoardImpl implements EnemyBoard {
    private CellState[][] grid;
    private int width, height, shipsRemaining;

    EnemyBoardImpl(int newWidth, int newHeight, int newShipsRemaining) {
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

    // TODO enemy board can only have certain states
    @Override
    public void updateCellState(Coordinates coordinates, CellState newState) {
        coordinates.checkBounds(width, height);
        if(newState == null) {
            throw new IllegalArgumentException("New cell state was null.");
        } else if(newState == CellState.SHIP_SUNK) {
            throw new IllegalArgumentException("Ship sunk is not a valid cell state for enemy board.");
        } else if(newState == CellState.SHIP_NOT_HIT) {
            throw new IllegalArgumentException("Ship not hit is not a valid cell state for enemy board.");
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
        return grid[coordinates.x][coordinates.y] != CellState.NOTHING;
    }

    @Override
    public CellState getCellState(Coordinates coordinates) {
        if(coordinates.x < 0 || coordinates.x >= width || coordinates.y < 0 || coordinates.y >= height) {
            throw new IllegalArgumentException("Coordinates " + coordinates.x + ", " + coordinates.y + " are out of bounds.");
        } else {
            return grid[coordinates.x][coordinates.y];
        }
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
