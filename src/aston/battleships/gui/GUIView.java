package aston.battleships.gui;

import aston.battleships.*;

import java.util.List;

/**
 * Created by cooperwd on 21/09/2017.
 */
public class GUIView implements View {
    WindowState state;

    Coordinates startingPosition;
    Orientation orientation;
    Coordinates move;

    public GUIView(int boardWidth, int boardHeight) {
        state = WindowState.NOT_WAITING;
        startingPosition = null;
        orientation = null;
        move = null;
    }

    void waitForShipPlacement() {
        state = WindowState.WAITING_FOR_STARTING_POSITION;
        // TODO: wait
    }
    void waitForMove() {
        state = WindowState.WAITING_FOR_MOVE;
        // TODO: wait
    }

    @Override
    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard) {

    }

    @Override
    public void welcomeUser() {

    }

    @Override
    public void viewInstructions() {

    }

    @Override
    public void announceGameOver(GameOverMessage message) {

    }

    @Override
    public void viewResultOfMove(Coordinates coordinates, CellState cellState) {

    }

    @Override
    public void viewResultOfEnemyMove(Coordinates coordinates, CellState cellState) {

    }

    @Override
    public void viewShipsLeftToPlace(List<Integer> shipLengths) {

    }
}
