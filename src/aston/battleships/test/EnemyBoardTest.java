package aston.battleships.test;

import aston.battleships.CellState;
import aston.battleships.Coordinates;
import aston.battleships.EnemyBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class EnemyBoardTest {
    EnemyBoard board;

    @Before
    public void setUp(){
        board = null;//new EnemyBoardImpl();

        // width = 7; height = 7; numberOfShipsRemaining 5;
    }

    @Test
    public void testUpdateCellState(){
        board.updateCellState(new Coordinates(1, 1), CellState.MISS);
        assertEquals(board.getCellState(new Coordinates(1, 1)), CellState.MISS);

    }

    @Test
    public void testGetNumberOfShipsRemaining() {
        assertEquals(board.getNumberOfShipsRemaining(), 5);
    }

    @Test
    public void testHasNotGuessed(){
        assertFalse(board.hasGuessed(new Coordinates(2,2)));
    }

    @Test
    public void testHasGuessed(){
        board.updateCellState(new Coordinates(3,3), CellState.MISS);
        assertTrue(board.hasGuessed(new Coordinates(3,3)));
    }

    @Test
    public void testGetCellState(){
        assertEquals(board.getCellState(new Coordinates(1,1)), CellState.NOTHING);
    }

    @Test
    public void testGetWidth(){
        assertEquals(board.getWidth(), 7);
    }

    @Test
    public void testGetHeight(){
        assertEquals(board.getHeight(), 7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCellStateOutOfBounds() {
        board.getCellState(new Coordinates(7,5));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testUpdateCellStateOutOfBounds() {
        board.updateCellState(new Coordinates(6,8), CellState.MISS);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testHasGuessedOutOfBounds() {
        board.hasGuessed(new Coordinates(-1,3));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCellStateNull() {
        board.updateCellState(new Coordinates(3,3), null);
    }
}
