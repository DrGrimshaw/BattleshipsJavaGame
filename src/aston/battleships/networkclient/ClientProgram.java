package aston.battleships.networkclient;

import aston.battleships.CellState;
import aston.battleships.Coordinates;
import aston.battleships.HumanPlayer;
import aston.battleships.Player;

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
            // TODO: lost connection to server
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

    private String receive() throws Player.QuitException {
        try {
            return in.readLine();
        } catch(IOException e) {
            System.err.println("The client has unexpectedly disconnected");
            throw new Player.QuitException();
        }
    }

    private Player handleStart(String command) {
    }

    private void handleGameOver(String command) {
    }

    private void handleMoveResponse(String command) {
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
