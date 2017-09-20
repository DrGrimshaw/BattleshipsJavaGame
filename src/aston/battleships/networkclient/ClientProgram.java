package aston.battleships.networkclient;

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

            while(true) {
                // keep asking the server what it wants us to do: i.e. wait for a command
                command = in.readLine();
                // dispatch the command

                if(command.startsWith("PLACE_SHIP")) {
                    handlePlaceShip(command);
                } else if(command.startsWith("CHOOSE_A_MOVE")) {
                    handleChooseAMove(command);
                } else if(command.startsWith("TAKE_A_HIT_AT")) {
                    handleTakeAHit(command);
                } else if(command.startsWith("MOVE_RESPONSE")) {
                    handleMoveResponse(command);
                } else if(command.startsWith("GAME_OVER")) {
                    handleGameOver(command);
                } else {

                }
            }
        } catch(IOException e) {
            // TODO: lost connection to server
        } finally {
            if(socket != null) {
                try { socket.close(); } catch(IOException ignored) {}
            }
        }
    }

    private Player handleStart(String command) {
    }

    private void handleGameOver(String command) {
    }

    private void handleMoveResponse(String command) {
    }

    private void handleTakeAHit(String command) {
    }

    private void handleChooseAMove(String command) {
    }

    private void handlePlaceShip(String command) {

    }
}
