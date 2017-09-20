package aston.battleships;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

/**
 * Created by dayachan on 20/09/2017.
 */
public class Program {
    public static final int WIDTH = 7;
    public static final int HEIGHT = 7;
    public static final Integer[] SHIP_LENGTHS = { 5, 4, 3, 3, 2 };

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(NetworkPlayer.PORT);
        new Game(
            // player 1
            new NetworkPlayer(listener, WIDTH, HEIGHT, SHIP_LENGTHS.length),
            // player 2
            new NetworkPlayer(listener, WIDTH, HEIGHT, SHIP_LENGTHS.length),
            //
            Arrays.asList(SHIP_LENGTHS)
        ).play();
    }
}
