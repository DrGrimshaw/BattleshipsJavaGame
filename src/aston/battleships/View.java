package aston.battleships;

import java.util.List;

/**
 * Created by cooperwd on 18/09/2017.
 */
public interface View {

    public enum GameOverMessage {
        YOU_WON, YOU_LOST,
        YOU_RESIGNED, ENEMY_RESIGNED,
        UNEXPECTED_QUIT
    }

    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard);
    public void welcomeUser();
    public void viewInstructions();
    public void announceGameOver(GameOverMessage message);
    public void viewResultOfMove(Coordinates coordinates, CellState cellState);
    public void viewResultOfEnemyMove(Coordinates coordinates, CellState cellState);
    public void viewShipsLeftToPlace(List<Integer> shipLengths);
}