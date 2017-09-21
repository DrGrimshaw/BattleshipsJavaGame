package aston.battleships.gui;

import aston.battleships.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Created by cooperwd on 21/09/2017.
 */
public class GUIView extends Frame implements View {
    WindowState state;

    Coordinates startingPosition;
    Orientation orientation;
    Coordinates move;

    private static final int CELL_SIZE = 30;
    private static final int PADDING = 10;
    private static final int ACTION_BOX_AND_TITLE_HEIGHT = CELL_SIZE*2;
    private static final int INSTRUCTIONS_HEIGHT = 200;

    public GUIView(int boardWidth, int boardHeight) {
        state = WindowState.NOT_WAITING;
        startingPosition = null;
        orientation = null;
        move = null;

        this.setSize((PADDING * 3) + (CELL_SIZE * boardWidth) * 2 + CELL_SIZE * 2,PADDING * 3 + CELL_SIZE * boardHeight + ACTION_BOX_AND_TITLE_HEIGHT * 2 + INSTRUCTIONS_HEIGHT);
        this.setVisible(true);

        addMouseListener(new MyMouselistener());
        addWindowListener(new MyWindowAdapter());
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
        switch(state){
            case NOT_WAITING:
                break;
            case WAITING_FOR_STARTING_POSITION:
                break;
            case WAITING_FOR_ORIENTATION:
                break;
            case WAITING_FOR_MOVE:
                break;
        }
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

    //TODO:
    private Coordinates pixelToCoordinates(int x, int y) {
        return null;
    }

    //TODO
    private int coordinatesToX(Coordinates coordinates) {
        return 0;
    }

    //TODO
    private int coordinatesToY(Coordinates coordinates) {
        return 0;
    }

    public class MyMouselistener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            switch(state) {
                case NOT_WAITING:
                    break;
                case WAITING_FOR_STARTING_POSITION:
                    startingPosition = pixelToCoordinates(e.getX(),e.getY());
                    break;
                case WAITING_FOR_ORIENTATION:
                    //TODO BUTTON SELECT
                    break;
                case WAITING_FOR_MOVE:
                    move = pixelToCoordinates(e.getX(),e.getY());
                    break;
            }
        }
    }
    class MyWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}
