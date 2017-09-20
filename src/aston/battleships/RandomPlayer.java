package aston.battleships;

import java.io.IOException;
import java.util.*;

/**
 * Created by lawtonel on 20/09/2017.
 */
public class RandomPlayer extends AbstractPlayer {
    private final Random random;

    public RandomPlayer(int newBoardWidth, int newBoardHeight, int newShipsRemaining) {
        super(newBoardWidth, newBoardHeight, newShipsRemaining);
        random = new Random();
    }

    @Override
    public void placeShipOnToPlayerBoard(int lengthOfShip) {
        while (true) {
            try {
                // generate random coordinates
                int x = random.nextInt(playerBoard.getWidth());
                int y = random.nextInt(playerBoard.getHeight());
                Coordinates coordinates = new Coordinates(x, y);
                coordinates.checkBounds(playerBoard.getWidth(), playerBoard.getHeight());

                // generate random orientation where true is right and false is down
                Ship ship = null;
                if(random.nextBoolean()) {
                    ship = new ShipImpl(lengthOfShip, coordinates, Orientation.RIGHT);
                } else {
                    ship = new ShipImpl(lengthOfShip, coordinates, Orientation.DOWN);
                }

                playerBoard.placeShip(ship);
                break;
            } catch(IndexOutOfBoundsException ignored) {
            } catch(PlayerBoard.ShipOverlapException ignored) {
            }
        }
    }

    @Override
    public Coordinates chooseMove() {
        while (true) {
            try {
                // generate random coordinates
                int x = random.nextInt(playerBoard.getWidth());
                int y = random.nextInt(playerBoard.getHeight());
                Coordinates coordinates = new Coordinates(x, y);
                coordinates.checkBounds(playerBoard.getWidth(), playerBoard.getHeight());

                if(!enemyBoard.hasGuessed(coordinates)) {
                    return coordinates;
                }
            } catch(IndexOutOfBoundsException e) {
            }
        }
    }

    @Override
    public View getView() {
        return new NullView();
    }
}
