package aston.battleships;

import java.util.Arrays;

/**
 * Created by dayachan on 20/09/2017.
 */
public class Program {
    public static void main(String[] args){
        Player player1 = new HumanPlayer(7,7, 5);
        Player player2 = new HumanPlayer(7,7, 5);
        Game game = new Game(player1, player2, Arrays.asList(5, 4, 3, 3, 2));
        game.play();
    }
}
