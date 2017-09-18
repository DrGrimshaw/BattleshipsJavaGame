package aston.battleships;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoardImpl implements PlayerBoard {
    int width;
    int height;
    CellState[][] cellStates;
    List<Ship>  ships;

    PlayerBoardImpl(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
        cellStates = new CellState[7][7];
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
    // todo: stub
    }

    @Override
    public CellState getCellState(Coordinates coordinates) {
        return cellStates[coordinates.x][coordinates.y];
    }

    @Override
    public boolean hasGuessedAlready(Coordinates coordinates) {
        if(getCellState(coordinates) == CellState.NOTHING){
            return false;
        }
        return true;
    }

    @Override
    public Ship getShip(Coordinates coordinates) {
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
}
