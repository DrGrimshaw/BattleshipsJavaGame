package aston.battleships.networkclient;

import aston.battleships.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dayachan on 20/09/2017.
 */
public class ClientProgram {
    BufferedReader in;
    PrintWriter out;
    Player player;

    ClientProgram() {

    }

    public void run() {
        Socket socket = null;
        try {
            // make connection to the server
            socket = new Socket("localhost", 9090);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
            out = new PrintWriter(socket.getOutputStream());

            // set up the game.
            String command = in.readLine();
            player = handleStart(command);

            while (true) {
                // keep asking the server what it wants us to do: i.e. wait for a command
                command = in.readLine();
                // dispatch the command

                if (command.startsWith("PLACE_SHIP")) {
                    handlePlaceShip(command);
                } else if (command.startsWith("CHOOSE_A_MOVE")) {
                    handleChooseAMove(command);
                } else if (command.startsWith("TAKE_A_HIT_AT")) {
                    handleTakeAHit(command);
                } else if (command.startsWith("MOVE_RESPONSE")) {
                    handleMoveResponse(command);
                } else if (command.startsWith("GAME_OVER")) {
                    handleGameOver(command);
                } else {

                }
            }
        } catch(Player.ResignException e) {
            // TODO:
        } catch(Player.QuitException e) {
            // TODO:
        } catch(IOException e) {
            System.err.println("The client has unexpectedly disconnected");
            System.exit(1);
        } finally {
            if(socket != null) {
                try { socket.close(); } catch(IOException ignored) {}
            }
        }
    }

    private void send(String data) {
        out.println(data);
        out.flush();
    }
    private void sendf(String data, Object... args) {
        out.printf(data + "\r\n", args);
        out.flush();
    }

    private Player handleStart(String command) {
        String[] c = command.split(" ");
        try {
            int width = Integer.parseInt(c[1]);
            int height = Integer.parseInt(c[2]);
            int shipsRemaining = Integer.parseInt(c[3]);
            return new HumanPlayer(width, height, shipsRemaining);
        } catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Failed to parse the START command: " + command);
            System.exit(1);
            return null;
        }
    }

    private void handleGameOver(String command) {
        String[] c = command.split(" ");
        try {
            View.GameOverMessage gameOverMessage = View.GameOverMessage.valueOf(c[1]);
            player.getView().announceGameOver(gameOverMessage);
        } catch(IllegalArgumentException | IndexOutOfBoundsException e) {
            System.err.println("Failed to parse the GAME_OVER command: " + command);
            System.exit(1);
        }
    }

    private void handleMoveResponse(String command) {
        String[] c = command.split(" ");
        try {
            Coordinates coordinates = new Coordinates(c[1]);
            CellState cellState = CellState.valueOf(c[2]);
            player.updateEnemyBoard(coordinates, cellState);
        } catch(Coordinates.MalformattedException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Failed to parse the MOVE_RESPONSE command: " + command);
            System.exit(1);
        }
    }

    private void handleTakeAHit(String command) throws Player.QuitException {
        try {
            String[] parts = command.split(" ");
            Coordinates coordinates = new Coordinates(parts[1]);
            CellState result = player.takeHit(coordinates);
            send(result.toString());
        } catch(Coordinates.MalformattedException e) {
            System.err.println("Error parsing TAKE_A_HIT command: " + command);
            System.exit(1);
        }
    }

    private void handleChooseAMove(String command) {
    }

    private void handlePlaceShip(String command) {

    }
}
