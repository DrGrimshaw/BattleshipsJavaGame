package aston.battleships.test;

import aston.battleships.CellState;
import aston.battleships.Coordinates;
import aston.battleships.PlayerBoard;
import aston.battleships.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by cooperwd on 18/09/2017.
 */
public class PlayerBoardTest {
    PlayerBoard playerBoard;
    Ship ship;
    @Before
    public void setUp(){
        int width,height;
        width = 7;
        height = 7;
        playerBoard = null; //new PlayerBoardImpl(width,height);
        ship = null; //new ShipImpl(length,orientation,startingCoordinates);
    }

    @Test
    public void getWidthTest(){
        assertEquals(playerBoard.getWidth(),7);
    }

    @Test
    public void getHeightTest(){
        assertEquals(playerBoard.getHeight(),7);
    }

    @Test
    public void placeShipTest(){
        playerBoard.placeShip(ship);
    }

    @Test
    public void testGetCellState(){

    }

}
