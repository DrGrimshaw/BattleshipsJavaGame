package aston.battleships;

public class Game {
    Player player1, player2, nextToPlay;

    Game(Player newPlayer1, Player newPlayer2){
        player1 = newPlayer1;
        player2 =  newPlayer2;
        nextToPlay = player1;
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

    public void play() {
        try {
            while (true) {
                Coordinates move = nextToPlay.chooseMove();
                CellState result = getOpponent().takeHit(move);
                nextToPlay.updateEnemyBoard(move, result);

                if(getOpponent().hasLost()) {
                    break;
                }

                nextToPlay = getOpponent();
            }
        } catch(Player.ResignedException e) {
            //TODO:

        }
    }
}
