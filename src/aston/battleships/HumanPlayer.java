package aston.battleships;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends AbstractPlayer {
    private final BufferedReader keyboard;

    public HumanPlayer(int newBoardWidth, int newBoardHeight, int newShipsRemaining) {
        super(newBoardWidth, newBoardHeight, newShipsRemaining);

        InputStreamReader isr = new InputStreamReader(System.in);
        keyboard = new BufferedReader(isr);
    }

    @Override
    public void placeShipOnToPlayerBoard(int lengthOfShip) {
        while (true) {
            try {
                System.out.println("Please enter the starting coordinates for this ship (length "+lengthOfShip+"):");
                System.out.print(">>> ");
                String coordinatesStr = keyboard.readLine();
                Coordinates coordinates = new Coordinates(coordinatesStr);
                coordinates.checkBounds(playerBoard.getWidth(), playerBoard.getHeight());

                Ship ship = null;
                while(ship == null) {
                    System.out.println("Please enter an orientation (right or down) for this ship:");
                    System.out.print(">>> ");
                    String orientationStr = keyboard.readLine();
                    if (orientationStr.equalsIgnoreCase("right")) {
                        ship = new ShipImpl(lengthOfShip, coordinates, Orientation.RIGHT);
                    } else if (orientationStr.equalsIgnoreCase("down")) {
                        ship = new ShipImpl(lengthOfShip, coordinates, Orientation.DOWN);
                    } else {
                        System.out.println("Invalid orientation entered");
                    }
                }

                playerBoard.placeShip(ship);
                break;

            } catch(Coordinates.MalformattedException e) {
                System.out.println("Invalid coordinates entered: " + e.getMessage());
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Ship placed out of bounds");
            } catch(PlayerBoard.ShipOverlapException e) {
                System.out.println("This ship overlaps with another");
            } catch(IOException e) {
                throw new IllegalStateException("IOException was thrown, with message: " + e.getMessage());
            }
        }
    }

    @Override
    public Coordinates chooseMove() throws ResignedException {
        while (true) {
            try {
                System.out.println("Please enter coordinates to fire at:");
                System.out.print(">>> ");
                String coordinatesStr = keyboard.readLine();
                if(coordinatesStr.equalsIgnoreCase("resign")) {
                    throw new ResignedException();
                }
                Coordinates coordinates = new Coordinates(coordinatesStr);
                if(enemyBoard.hasGuessed(coordinates)) {
                    System.out.println("Coordinates already guessed.");
                    continue;
                } else {
                    return coordinates;
                }

            } catch(Coordinates.MalformattedException e) {
                System.out.println("Invalid coordinates entered.");
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Coordinates out of bounds.");
            } catch(IOException e) {
                throw new IllegalStateException("IOException was thrown, with message: " + e.getMessage());
            }
        }
    }

    @Override
    public View getView() {
        return new TextView();
    }
}
