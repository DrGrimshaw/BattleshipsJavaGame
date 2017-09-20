package aston.battleships;

import java.util.List;

public class Game {
    Player player1, player2, nextToPlay;
    List<Integer> shipLengths;

    Game(Player newPlayer1, Player newPlayer2, List<Integer> shipLengths){
        player1 = newPlayer1;
        player2 =  newPlayer2;
        nextToPlay = player1;
        this.shipLengths = shipLengths;
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

    private void placeShips(Player player){
        player.getView().viewShipsLeftToPlace(shipLengths);
        for(int shipLength : shipLengths) {
            player.viewState();
            player.placeShipOnToPlayerBoard(shipLength);
        }
    }

    public void play() {
        try {
            player1.getView().welcomeUser();
            player1.getView().viewInstructions();

            player2.getView().welcomeUser();
            player2.getView().viewInstructions();

            placeShips(player1);
            placeShips(player2);

            while (true) {
                View view = nextToPlay.getView();
                nextToPlay.viewState();
                Coordinates move = nextToPlay.chooseMove();
                CellState result = getOpponent().takeHit(move);
                nextToPlay.updateEnemyBoard(move, result);
                view.viewResultOfMove(move, result);
                getOpponent().getView().viewResultOfEnemyMove(move, result);


                if(getOpponent().hasLost()) {
                    view.announceGameOver(View.GameOverMessage.WON);
                    getOpponent().getView().announceGameOver(View.GameOverMessage.LOST);
                    break;
                }

                nextToPlay = getOpponent();
            }
        } catch(Player.ResignException e) {
            nextToPlay.getView().announceGameOver(View.GameOverMessage.RESIGNED);
            getOpponent().getView().announceGameOver(View.GameOverMessage.ENEMY_RESIGNED);
        } catch(Player.QuitException e) {
            // TODO:
            System.err.println("Player quit unexpectedly");
        }
    }
}
