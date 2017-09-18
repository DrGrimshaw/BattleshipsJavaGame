package aston.battleships.test;

import aston.battleships.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by cooperwd on 18/09/2017.
 */
public class PlayerBoardTest {
    PlayerBoard playerBoard;
    Ship ship;

    @Before
    public void setUp() {
        playerBoard = null; //new PlayerBoardImpl(7, 7);
        ship = null; //new ShipImpl(3, new Coordinates(0, 0), Orientation.RIGHT);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructIncorrect() {
        //new PlayerBoardImpl(0, 0);
    }

    @Test
    public void getWidthTest() {
        assertEquals(playerBoard.getWidth(), 7);
    }

    @Test
    public void getHeightTest() {
        assertEquals(playerBoard.getHeight(), 7);
    }

    @Test
    public void placeShipTest() {
        playerBoard.placeShip(ship);
        assertEquals(playerBoard.getShip(new Coordinates(0,0)), ship);
    }

    @Test (expected = IllegalArgumentException.class)
    public void placeNullShip() {
        playerBoard.placeShip(null);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void placeShipTestOutsideBounds() {
        Ship ship2 = new ShipImpl(3, new Coordinates(10, 10), Orientation.RIGHT);
        playerBoard.placeShip(ship2);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void placeShipLengthTestOutsideBounds() {
        Ship ship2 = new ShipImpl(3, new Coordinates(7, 7), Orientation.DOWN);
        playerBoard.placeShip(ship2);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void placeShipsOverlapTestOutsideBounds() {
        playerBoard.placeShip(ship);
        Ship ship2 = new ShipImpl(3, new Coordinates(0, 1), Orientation.DOWN);
        playerBoard.placeShip(ship2);
    }

    @Test
    public void testGetCellStateNothing() {
        assertEquals(playerBoard.getCellState(new Coordinates(0,0)),CellState.NOTHING);
    }

    @Test
    public void testGetCellStateMiss() {
        playerBoard.takeAHit(new Coordinates(0,0));
        assertEquals(playerBoard.getCellState(new Coordinates(0,0)),CellState.MISS);
    }

    @Test
    public void testGetCellStateHit() {
        playerBoard.placeShip(ship);
        playerBoard.getShip(new Coordinates(0,0));
        assertEquals(playerBoard.getCellState(new Coordinates(0,0)),CellState.SHIP_HIT);
    }

    @Test
    public void testGetCellStateSunk() {
        playerBoard.placeShip(ship);
        playerBoard.takeAHit(new Coordinates(0,0));
        playerBoard.takeAHit(new Coordinates(0,1));
        playerBoard.takeAHit(new Coordinates(0,2));
        assertEquals(playerBoard.getCellState(new Coordinates(0,2)),CellState.SHIP_SUNK);
    }

    @Test
    public void testHasGuessedAlready() {
        playerBoard.takeAHit(new Coordinates(0,0));
        assertTrue(playerBoard.hasGuessedAlready(new Coordinates(0,0)));
    }

    @Test
    public void testShipsRemaining() {
        playerBoard.placeShip(ship);
        assertEquals(playerBoard.getNumberOfShipsRemaining(),1);
    }

    @Test
    public void testGetShips() {
        playerBoard.placeShip(ship);
        assertEquals(playerBoard.getShips().get(0),ship);
    }

    @Test
    public void testGameOverFalse() {
        assertFalse(playerBoard.isGameOver());
    }

    @Test
    public void testGameOverTrue() {
        playerBoard.placeShip(ship);
        playerBoard.takeAHit(new Coordinates(0,0));
        playerBoard.takeAHit(new Coordinates(0,1));
        playerBoard.takeAHit(new Coordinates(0,2));
        assertTrue(playerBoard.isGameOver());
    }

}
