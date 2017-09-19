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
        playerBoard = new PlayerBoardImpl(7, 7);
        ship = new ShipImpl(3, new Coordinates(0, 0), Orientation.RIGHT);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructIncorrect() {
        new PlayerBoardImpl(0, 5);
    }

    @Test
    public void testGetWidth() {
        assertEquals(playerBoard.getWidth(), 7);
    }

    @Test
    public void testGetHeight() {
        assertEquals(playerBoard.getHeight(), 7);
    }

    @Test
    public void testPlaceShip() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        assertEquals(playerBoard.getShip(new Coordinates(0,0)), ship);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPlaceNullShip() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(null);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testPlaceShipOutsideBounds() throws PlayerBoard.ShipOverlapException {
        Ship ship2 = new ShipImpl(3, new Coordinates(10, 10), Orientation.RIGHT);
        playerBoard.placeShip(ship2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testPlaceShipLengthOutsideBounds() throws PlayerBoard.ShipOverlapException {
        Ship ship2 = new ShipImpl(3, new Coordinates(7, 7), Orientation.DOWN);
        playerBoard.placeShip(ship2);
    }

    @Test (expected = PlayerBoard.ShipOverlapException.class)
    public void testPlaceShipsOverlap() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        Ship ship2 = new ShipImpl(3, new Coordinates(1, 0), Orientation.DOWN);
        playerBoard.placeShip(ship2);
    }

    @Test (expected = PlayerBoard.ShipOverlapException.class)
    public void testPlaceShipsOverlap2() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(new ShipImpl(3, new Coordinates(2, 0), Orientation.DOWN));
        playerBoard.placeShip(new ShipImpl(3, new Coordinates(0, 2), Orientation.RIGHT));
    }

    @Test (expected = PlayerBoard.ShipOverlapException.class)
    public void testPlaceShipsOverlapTest3() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(new ShipImpl(5, new Coordinates(2, 0), Orientation.DOWN));
        playerBoard.placeShip(new ShipImpl(4, new Coordinates(0, 3), Orientation.RIGHT));
    }

    @Test (expected = PlayerBoard.ShipOverlapException.class)
    public void testPlaceShipsOverlapTest4() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(new ShipImpl(3, new Coordinates(2, 0), Orientation.DOWN));
        playerBoard.placeShip(new ShipImpl(3, new Coordinates(2, 2), Orientation.DOWN));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testPlaceShipLongerThanGridOutsideBounds() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(new ShipImpl(8, new Coordinates(2, 0), Orientation.DOWN));
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
    public void testGetCellStateHit() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        playerBoard.takeAHit(new Coordinates(0,0));
        assertEquals(playerBoard.getCellState(new Coordinates(0,0)),CellState.SHIP_HIT);
    }

    @Test
    public void testGetCellStateSunk() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        playerBoard.takeAHit(new Coordinates(0,0));
        playerBoard.takeAHit(new Coordinates(1,0));
        playerBoard.takeAHit(new Coordinates(2,0));
        assertEquals(playerBoard.getCellState(new Coordinates(2,0)),CellState.SHIP_SUNK);
    }

    @Test
    public void testHasGuessedAlready() {
        playerBoard.takeAHit(new Coordinates(0,0));
        assertTrue(playerBoard.hasGuessedAlready(new Coordinates(0,0)));
    }

    @Test
    public void testShipsRemaining() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        assertEquals(playerBoard.getNumberOfShipsRemaining(),1);
    }

    @Test
    public void testGetShips() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        assertEquals(playerBoard.getShips().get(0),ship);
    }

    @Test
    public void testGetShipsUnmodifiable() throws PlayerBoard.ShipOverlapException {
        // Testing both cases for either any exception that could be thrown from mutating list
        // or mutating the list doesn't throw an exception but it doesn't mutate the list returned
        // from the method getShips().
        playerBoard.placeShip(ship);
        try {
            List<Ship> ships = playerBoard.getShips();
            ships.clear();
            assertEquals(playerBoard.getShips().size(), 1);
        } catch(Exception ignored) {}
    }

    @Test
    public void testNotAllShipsSunk() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        assertFalse(playerBoard.areAllShipsSunk());
    }

    @Test
    public void testAllShipsAreSunk() throws PlayerBoard.ShipOverlapException {
        playerBoard.placeShip(ship);
        playerBoard.takeAHit(new Coordinates(0,0));
        playerBoard.takeAHit(new Coordinates(1,0));
        playerBoard.takeAHit(new Coordinates(2,0));
        assertTrue(playerBoard.areAllShipsSunk());
    }

}
