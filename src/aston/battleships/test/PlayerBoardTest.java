package aston.battleships.test;

import aston.battleships.CellState;
import aston.battleships.Coordinates;
import aston.battleships.PlayerBoard;
import aston.battleships.Ship;
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
        int width, height;
        width = 7;
        height = 7;
        playerBoard = null; //new PlayerBoardImpl(width, height);
        ship = null; //new ShipImpl(3, new Coordinates(0,0), Orientation.RIGHT);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructIncorrect() {
        //new PlayerBoardImpl(0,0);
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
