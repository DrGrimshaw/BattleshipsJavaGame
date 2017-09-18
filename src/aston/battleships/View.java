package aston.battleships;

import java.util.List;

/**
 * Created by cooperwd on 18/09/2017.
 */
public interface View {

    public enum GameOverMessage{

        WON,LOST, RESIGNED, ENEMY_RESIGNED;
    }

    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard);
    public void welcomeUser();
    public void viewInstructions();
    public void announceGameOver(GameOverMessage message);
    public void viewResultOfMove(CellState cellState);
    public void viewShipsLeftToPlace(List<Integer> shipLengths);
}
