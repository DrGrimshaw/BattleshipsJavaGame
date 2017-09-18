package aston.battleships;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoardImpl implements PlayerBoard {
    int width;
    int height;
    CellState[][] cellStates;
    List<Ship> ships;

    public PlayerBoardImpl(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
        cellStates = new CellState[newWidth][newHeight];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                cellStates[x][y] = CellState.NOTHING;
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

        // TODO: test if new ship will be out of bounds
        if (cellStates[x][y] ) {

        }
        // TODO: test if new ship overlaps any previous ship

        // otherwise,
        // TODO: update the cell states for the new ship

        // TODO: add the ship to the list of ships
    }

    @Override
    public CellState getCellState(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        return cellStates[coordinates.x][coordinates.y];
    }

    @Override
    public boolean hasGuessedAlready(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        if(getCellState(coordinates) == CellState.NOTHING){
            return false;
        }
        return true;
    }

    @Override
    public Ship getShip(Coordinates coordinates) {
        coordinates.checkBounds(width, height);
        for (Ship ship : ships) {
            if (ship.isAt(coordinates)) {
                return ship;
            }
        }
        return null;
    }

    @Override
    public int getNumberOfShipsRemaining() {
        int counter = 0;
        for (Ship ship : ships){
            if (!ship.isSunk()) {
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
        // TODO
        return null;
    }
}
