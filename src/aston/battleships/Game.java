package aston.battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    Player player1, player2, nextToPlay;
    List<Integer> shipLengths;

    Game(Player newPlayer1, Player newPlayer2){
        player1 = newPlayer1;
        player2 =  newPlayer2;
        nextToPlay = player1;
        shipLengths = Arrays.asList(5, 4, 3, 3, 2);
    }

    public Player getNextToPlay(){
        return nextToPlay;
    }

    public Player getOpponent() {
        if(nextToPlay == player1){
            return player2;
        } else {
            return player1;
        }

    }

    private void placeShips(){
        for(int shipLength : shipLengths) {
            player1.placeShipOnToPlayerBoard(shipLength);
        }
        for(int shipLength : shipLengths) {
            player2.placeShipOnToPlayerBoard(shipLength);
        }
    }

    public void play() {
        try {
            placeShips();

            while (true) {
                View view = nextToPlay.getView();
                nextToPlay.viewState();
                Coordinates move = nextToPlay.chooseMove();
                CellState result = getOpponent().takeHit(move);
                nextToPlay.updateEnemyBoard(move, result);
                view.viewResultOfMove(result);

                if(getOpponent().hasLost()) {
                    view.announceGameOver(View.GameOverMessage.WON);
                    getOpponent().getView().announceGameOver(View.GameOverMessage.LOST);
                    break;
                }

                nextToPlay = getOpponent();
            }
        } catch(Player.ResignedException e) {
            nextToPlay.getView().announceGameOver(View.GameOverMessage.RESIGNED);
            getOpponent().getView().announceGameOver(View.GameOverMessage.ENEMY_RESIGNED);
        }
    }
}
