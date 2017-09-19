package aston.battleships;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer implements Player {
    private final BufferedReader keyboard;

    private final PlayerBoard playerBoard;
    private final EnemyBoard enemyBoard;

    public HumanPlayer(int newBoardWidth, int newBoardHeight, int newShipsRemaining) {
        playerBoard = new PlayerBoardImpl(newBoardWidth, newBoardHeight);
        enemyBoard = new EnemyBoardImpl(newBoardWidth, newBoardHeight, newShipsRemaining);

        InputStreamReader isr = new InputStreamReader(System.in);
        keyboard = new BufferedReader(isr);
    }

    @Override
    public void placeShipOnToPlayerBoard(int lengthOfShip) {
        while (true) {
            try {
                System.out.println("Please enter the starting coordinates for this ship:");
                // >>>
                String coordinatesStr = keyboard.readLine();
                Coordinates coordinates = new Coordinates(coordinatesStr);
                coordinates.checkBounds(playerBoard.getWidth(), playerBoard.getHeight());

                System.out.println("Please enter an orientation (right or down) for this ship:");
                Ship ship = null;
                while(ship == null) {
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
                String coordinatesStr = keyboard.readLine();
                if(coordinatesStr.equalsIgnoreCase("resign")) {
                    throw new ResignedException();
                }
                Coordinates coordinates = new Coordinates(coordinatesStr);
                coordinates.checkBounds(enemyBoard.getWidth(), enemyBoard.getHeight());
                // TODO: check if already guessed

                return coordinates;
            } catch(Coordinates.MalformattedException e) {
                System.out.println("Invalid coordinates entered.");
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Coordinates out of bounds");
            } catch(IOException e) {
                throw new IllegalStateException("IOException was thrown, with message: " + e.getMessage());
            }
        }
    }

    @Override
    public CellState takeHit(Coordinates coordinates) {
        return playerBoard.takeAHit(coordinates);
    }

    @Override
    public boolean isGameOver() {
        return playerBoard.isGameOver();
    }

    @Override
    public boolean hasAlreadyGuessed(Coordinates coordinates) {
        return playerBoard.hasGuessedAlready(coordinates);
    }

    @Override
    public void viewState() {
        View view = new TextView();
        view.viewBoards(playerBoard, enemyBoard);
    }

    @Override
    public void updateEnemyBoard(Coordinates coordinates, CellState cellState) {
        enemyBoard.updateCellState(coordinates, cellState);
    }
}
