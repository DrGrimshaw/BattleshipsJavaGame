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
    String hostname;

    ClientProgram(String hostname) {
        this.hostname = hostname;
    }

    public static void main(String[] args) {
        new ClientProgram("werp.site").run();
    }

    public void run() {
        Socket socket = null;
        try {
            // make connection to the server
            socket = new Socket(hostname, NetworkPlayer.PORT);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
            out = new PrintWriter(socket.getOutputStream());

            // set up the game.
            String command = receive();
            handleStart(command);

            while (true) {
                // keep asking the server what it wants us to do: i.e. wait for a command
                command = receive();
                // dispatch the command

                if(command.trim().isEmpty()) {
                    // do nothing
                } else if(command.startsWith("PLACE_SHIP")) {
                    handlePlaceShip(command);
                } else if (command.startsWith("CHOOSE_A_MOVE")) {
                    handleChooseAMove(command);
                } else if (command.startsWith("TAKE_HIT_AT")) {
                    handleTakeAHit(command);
                } else if (command.startsWith("MOVE_RESPONSE")) {
                    handleMoveResponse(command);
                } else if (command.startsWith("GAME_OVER")) {
                    handleGameOver(command);
                } else {
                    System.err.println("Invalid command: " + command);
                    System.exit(1);
                }
            }
        } catch(Player.ResignException e) {
            send("RESIGN");
        } catch(Player.QuitException e) {
            send("RESIGN");
            System.err.println("The player has quit unexpectedly");
            System.exit(1);
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
    private String receive() throws IOException {
        String command = in.readLine();
        if(command == null) {
            throw new IOException();
        }
        return command;
    }

    private void handleStart(String command) {
        String[] c = command.split(" ");
        try {
            int width = Integer.parseInt(c[1]);
            int height = Integer.parseInt(c[2]);
            int shipsRemaining = Integer.parseInt(c[3]);
            player = new HumanPlayer(width, height, shipsRemaining);
            player.getView().welcomeUser();
            player.getView().viewInstructions();
        } catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Failed to parse the START command: " + command);
            System.exit(1);
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
            player.getView().viewResultOfMove(coordinates, cellState);
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
            player.getView().viewResultOfEnemyMove(coordinates, result);
        } catch(Coordinates.MalformattedException e) {
            System.err.println("Error parsing TAKE_A_HIT command: " + command);
            System.exit(1);
        }
    }

    private void handleChooseAMove(String command) throws Player.QuitException {
        player.viewState();
        Coordinates move = player.chooseMove();
        send(move.toString());
    }


    private void handlePlaceShip(String command) {
        player.viewState();
        try {
            String[] parts = command.split(" ");
            player.placeShipOnToPlayerBoard(Integer.valueOf(parts[1]));
        } catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Failed to parse the PLACE_SHIP command: " + command);
            System.exit(1);
        }
    }
}
