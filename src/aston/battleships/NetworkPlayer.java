package aston.battleships;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Created by cooperwd on 20/09/2017.
 */
public class NetworkPlayer implements Player, View {
    public static final int PORT = 9090;

    private final BufferedReader in;
    private final PrintWriter out;
    private int shipsRemaining;

    public NetworkPlayer(int width, int height, int newShipsRemaining) throws IOException {
        if(width <= 0 || height <= 0){
            throw new IllegalArgumentException("You cannot make a grid size of "
                    + width + " by " + height );
        } else if(newShipsRemaining <= 0) {
            throw new IllegalArgumentException("You cannot make a grid with no ships remaining.");
        }

        shipsRemaining = newShipsRemaining;

        ServerSocket listener = new ServerSocket(PORT);
        Socket socket = listener.accept();
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        in = new BufferedReader(isr);
        out = new PrintWriter(socket.getOutputStream());

        out.println("START "+width+" "+height+" "+newShipsRemaining);
    }

    @Override
    public void placeShipOnToPlayerBoard(int lengthOfShip) {
        out.println("PLACE_SHIP "+lengthOfShip);
    }

    @Override
    public Coordinates chooseMove() throws QuitException {
        return null;
    }

    @Override
    public CellState takeHit(Coordinates coordinates) throws QuitException {
        out.println("TAKE_HIT_AT " + coordinates);

        try {
            String response = in.readLine();
            if(response.equals("MISS")) {
                return CellState.MISS;
            } else if(response.equals("SHIP_HIT")) {
                return CellState.SHIP_HIT;
            } else if(response.equals("SHIP_SUNK")) {
                if(shipsRemaining == 0) {
                    throw new QuitException();
                }

                shipsRemaining--;
                return CellState.SHIP_SUNK;
            } else {
                throw new QuitException();
            }
        } catch (IOException e) {
            throw new QuitException();
        }
    }

    @Override
    public boolean hasLost() {
        return shipsRemaining == 0;
    }

    @Override
    public boolean hasAlreadyGuessed(Coordinates coordinates) {
        return false;
    }

    @Override
    public void viewState() {

    }

    @Override
    public void updateEnemyBoard(Coordinates coordinates, CellState cellState) {

    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard) {

    }

    @Override
    public void welcomeUser() {

    }

    @Override
    public void viewInstructions() {

    }

    @Override
    public void announceGameOver(GameOverMessage message) {

    }

    @Override
    public void viewResultOfMove(CellState cellState) {

    }

    @Override
    public void viewShipsLeftToPlace(List<Integer> shipLengths) {

    }
}
