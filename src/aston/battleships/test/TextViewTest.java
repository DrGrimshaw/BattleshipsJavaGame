package aston.battleships.test;

import aston.battleships.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cooperwd on 19/09/2017.
 */
public class TextViewTest {
    PlayerBoard playerBoard;
    EnemyBoard enemyBoard;
    Ship smallShip, mediumShip, largeShip;
    TextView view;

    @Before
    public void setup(){
        //Boards
        playerBoard = new PlayerBoardImpl(7,7);
        enemyBoard = new EnemyBoardImpl(7,7, 6);
        // Ships
        smallShip = new ShipImpl(2, new Coordinates(0,0), Orientation.DOWN);
        mediumShip = new ShipImpl(3, new Coordinates(2,1), Orientation.DOWN);
        largeShip = new ShipImpl(4, new Coordinates(0,6), Orientation.RIGHT);
        // Place ships onto player board
        playerBoard.placeShip(smallShip);
        playerBoard.placeShip(mediumShip);
        playerBoard.placeShip(largeShip);
        // Make a hit on 2 ships
        playerBoard.takeAHit(new Coordinates(0,1));
        playerBoard.takeAHit(new Coordinates(1,6));
        playerBoard.takeAHit(new Coordinates(5,5));
        // Try to hit enemy
        enemyBoard.updateCellState(new Coordinates(0,3), CellState.MISS);
        enemyBoard.updateCellState(new Coordinates(1,4), CellState.MISS);
        enemyBoard.updateCellState(new Coordinates(0,0), CellState.MISS);
        enemyBoard.updateCellState(new Coordinates(6,6), CellState.SHIP_HIT);
        enemyBoard.updateCellState(new Coordinates(5,6), CellState.SHIP_HIT);
        enemyBoard.updateCellState(new Coordinates(4,6), CellState.SHIP_HIT);
    }

    @Test
    public void viewBoards(){
        view = new TextView();
        view.viewBoards(playerBoard, enemyBoard);
    }



}
