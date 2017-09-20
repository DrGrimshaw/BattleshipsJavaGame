package aston.battleships;

import java.util.List;

/**
 * Created by cooperwd on 20/09/2017.
 */
public class NullView implements View {
    @Override
    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard) {

    }

    @Override
    public void welcomeUser() {

    }

    @Override
    public void viewInstructions() {

    }

    @Override
    public void announceGameOver(GameOverMessage message) {

    }

    @Override
    public void viewResultOfMove(CellState cellState) {

    }

    @Override
    public void viewShipsLeftToPlace(List<Integer> shipLengths) {

    }
}
