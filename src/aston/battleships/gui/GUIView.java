package aston.battleships.gui;

import aston.battleships.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;

/**
 * Created by cooperwd on 21/09/2017.
 */
public class GUIView extends Frame implements View {
    static final String instructions =
            "- Before the game starts you will be asked to place your battleships\n" +
            "- You can click on a square on the board to place a ship\n" +
            "- When the game starts you can select a position on the enemy board to fire on\n" +
            "- You cannot click to fire on the same place twice\n" +
            "- The game is only over when you or your enemy have no battleships left\n" +
            "- You can also resign from the game by closing the window\n" +
            "~ GOOD LUCK PLAYER ~";

    WindowState state;

    Coordinates startingPosition;
    Orientation orientation;
    Coordinates move;

    private static final int CELL_SIZE = 30;
    private static final int PADDING = 10;
    private static final int ACTION_BOX_AND_TITLE_HEIGHT = CELL_SIZE*2;
    private static final int INSTRUCTIONS_HEIGHT = 200;

    private final int boardWidth;
    private final int boardHeight;


    public GUIView(int boardWidth, int boardHeight) {
        state = WindowState.NOT_WAITING;
        startingPosition = null;
        orientation = null;
        move = null;

        this.setSize((PADDING * 3) + (CELL_SIZE * boardWidth) * 2 + CELL_SIZE * 2,PADDING * 3 + CELL_SIZE * boardHeight + ACTION_BOX_AND_TITLE_HEIGHT * 2 + INSTRUCTIONS_HEIGHT + CELL_SIZE);
        this.setVisible(true);

        addMouseListener(new MyMouselistener());
        addWindowListener(new MyWindowAdapter());

        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;


        // TODO:
        Graphics g = getGraphics();
        g.drawString(" BATTLE SHIPS ", boardWidth, boardHeight);
        FontMetrics fm = g.getFontMetrics();
        int x = (boardWidth - fm.stringWidth(" BATTLE SHIPS")) / 2;
        int y = (fm.getAscent() + (boardHeight - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(" BATTLE SHIPS", x, y);
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

    private void writeToAction() {


    }

    @Override
    public void welcomeUser() {
        JOptionPane.showMessageDialog(null, "WELCOME TO BATTLESHIP", "BATTLESHIPS GAME", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void viewInstructions() {
        Graphics g = getGraphics();
        g.drawString(instructions, PADDING, PADDING * 2 + ACTION_BOX_AND_TITLE_HEIGHT );
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

    private boolean withinPlayerBoard(int x, int y) {
        x -= PADDING + CELL_SIZE;
        y -= PADDING + ACTION_BOX_AND_TITLE_HEIGHT + CELL_SIZE;
        return x >= 0 && x < CELL_SIZE * boardWidth && y >= 0 && y < CELL_SIZE * boardHeight;
    }

    private boolean withinEnemyBoard(int x, int y){
        x -= PADDING*2 + CELL_SIZE*2 + CELL_SIZE*boardWidth;
        y -= PADDING + ACTION_BOX_AND_TITLE_HEIGHT + CELL_SIZE;
        return x >= 0 && x < CELL_SIZE * boardWidth && y >= 0 && y < CELL_SIZE * boardHeight;
    }

    //TODO:
    private Coordinates pixelToCoordinates(int x, int y) {

        if(withinPlayerBoard(x,y)) {
            x = (x - PADDING - CELL_SIZE) / CELL_SIZE;
            y = (y - PADDING - ACTION_BOX_AND_TITLE_HEIGHT - CELL_SIZE) / CELL_SIZE;

            return new Coordinates(x, y);
        } else if(withinEnemyBoard(x,y)) {
            x = (x - PADDING*2 - CELL_SIZE*2 - CELL_SIZE*boardWidth) / CELL_SIZE;
            y = (y - PADDING - ACTION_BOX_AND_TITLE_HEIGHT - CELL_SIZE) / CELL_SIZE;

            return new Coordinates(x, y);
        } else {
            return null;

        }
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
