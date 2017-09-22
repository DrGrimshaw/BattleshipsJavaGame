package aston.battleships;

import java.util.List;

public class Game implements Runnable {
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

    public Player getEnemy() {
        if(nextToPlay == player1){
            return player2;
        } else {
            return player1;
        }
    }

    private void placeShips(Player player) throws Player.ResignException {
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
                CellState result = getEnemy().takeHit(move);
                nextToPlay.updateEnemyBoard(move, result);
                view.viewResultOfMove(move, result);
                getEnemy().getView().viewResultOfEnemyMove(move, result);


                if(getEnemy().hasLost()) {
                    view.announceGameOver(View.GameOverMessage.YOU_WON);
                    getEnemy().getView().announceGameOver(View.GameOverMessage.YOU_LOST);
                    break;
                }

                nextToPlay = getEnemy();
            }
        } catch(Player.ResignException e) {
            nextToPlay.getView().announceGameOver(View.GameOverMessage.YOU_RESIGNED);
            getEnemy().getView().announceGameOver(View.GameOverMessage.ENEMY_RESIGNED);
        } catch(Player.QuitException e) {
            player1.getView().announceGameOver(View.GameOverMessage.UNEXPECTED_QUIT);
            player2.getView().announceGameOver(View.GameOverMessage.UNEXPECTED_QUIT);
            System.err.println("Player quit unexpectedly");
        }
    }

    @Override
    public void run() {
        play();
    }
}
