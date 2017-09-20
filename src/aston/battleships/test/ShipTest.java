package aston.battleships.test;
import static org.junit.Assert.*;
import aston.battleships.*;
import org.junit.Before;
import org.junit.Test;

public class ShipTest {
    private Ship ship;
    private Coordinates coordinates;

    @Before
    public void setUp() {
        coordinates = new Coordinates(1,2);
        ship = new ShipImpl(3, coordinates, Orientation.RIGHT);
    }

    @Test
    public void testGetLength() {
        assertEquals(ship.getLength(), 3);
    }

    @Test
    public void testIsAt() {
        assertTrue(ship.isAt(coordinates));
    }

    @Test
    public void testIsAtEndOfShip() {
        Coordinates endOfShip = new Coordinates(3,2);
        assertTrue(ship.isAt(endOfShip));
    }

    @Test
    public void testIsNotAtRight() {
        Coordinates noShipRight = new Coordinates(4,2);
        assertFalse(ship.isAt(noShipRight));
    }

    @Test
    public void testIsNotAtDown() {
        Coordinates noShipDown = new Coordinates(1,3);
        assertFalse(ship.isAt(noShipDown));
    }

    @Test
    public void testNoHits() {
        assertFalse(ship.isSunk());
    }

    @Test
    public void testTakeAHit() {
        ship.takeAHit();
        assertFalse(ship.isSunk());
    }

    @Test
    public void testIsSunk() {
        ship.takeAHit();
        ship.takeAHit();
        ship.takeAHit();
        assertTrue(ship.isSunk());
    }

    @Test
    public void testStartingPosition() {
        assertEquals(ship.getStartingPosition(), new Coordinates(1,2));
    }

    @Test
    public void testGetEndPosition() {
        assertEquals(ship.getEndPosition(), new Coordinates(3,2));
    }


    @Test(expected = IllegalStateException.class)
    public void testTooManyHits() {
        ship.takeAHit();
        ship.takeAHit();
        ship.takeAHit();
        ship.takeAHit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLength() {
        new ShipImpl(0, coordinates, Orientation.RIGHT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullCoordinates() {
        new ShipImpl(3, null, Orientation.RIGHT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullOrientation() {
        new ShipImpl(3, coordinates, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCoordinates() {
        Coordinates newCoordinates = new Coordinates(-1, 0);
        new ShipImpl(3, newCoordinates, Orientation.RIGHT);
    }

    @Test
    public void testGetAllCoordinates() {
        Coordinates[] allCoordinates = ship.getAllCoordinates();
        assertEquals(allCoordinates[0], ship.getStartingPosition());
        assertEquals(allCoordinates[1], new Coordinates(2,2));
        assertEquals(allCoordinates[2], ship.getEndPosition());
    }
}
