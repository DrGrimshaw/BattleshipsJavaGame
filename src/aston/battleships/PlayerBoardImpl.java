package aston.battleships;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoardImpl implements PlayerBoard {
    private final int width, height;
    private final CellState[][] grid;
    private final List<Ship> ships;

    public PlayerBoardImpl(int newWidth, int newHeight) {
        // TODO: check for legal arguments

        width = newWidth;
        height = newHeight;

        grid = new CellState[newWidth][newHeight];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                grid[x][y] = CellState.NOTHING;
            }
        }

        ships = new ArrayList<Ship>();
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void placeShip(Ship ship) {

        // test if new ship will be out of bounds
        // we don't need to check startingPosition is in bounds because Ship constructor guarantees its coordinates are non-negative
        Coordinates startingPosition = ship.getStartingPosition();
        Orientation orientation = ship.getOrientation();
        if (orientation == Orientation.RIGHT) {
            new Coordinates(startingPosition.x + ship.getLength() - 1, startingPosition.y).checkBounds(width, height);
        } else {
            new Coordinates(startingPosition.x , startingPosition.y + ship.getLength() - 1).checkBounds(width, height);
        }

        // TODO: test if new ship overlaps any previous ship

        // otherwise,
        // TODO: update the cell states for the new ship

        // TODO: add the ship to the list of ships
    }

    @Override
    public CellState getCellState(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        return grid[coordinates.x][coordinates.y];
    }

    @Override
    public boolean hasGuessedAlready(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        // TODO: a ship not hit is not yet guessed
        return getCellState(coordinates) != CellState.NOTHING;
    }

    @Override
    public Ship getShip(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        for(Ship ship : ships) {
            if(ship.isAt(coordinates)) {
                return ship;
            }
        }
        return null;
    }

    @Override
    public int getNumberOfShipsRemaining() {
        int counter = 0;
        for(Ship ship : ships){
            if(!ship.isSunk()) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public boolean isGameOver() {
        return getNumberOfShipsRemaining() == 0;
    }

    @Override
    public CellState takeAHit(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        // TODO: check they haven't already hit here
        // TODO: find the ship, if it exists, make it take a hit.
        // TODO: return correct cell state
        return null;
    }
}
