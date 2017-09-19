package aston.battleships;

import java.util.List;

/**
 * Created by cooperwd on 19/09/2017.
 */
public class TextView implements View {

    public static String padCenter(String input, int newLength) {
        int inputLength = input.length();
        int totalSpaces = newLength - inputLength;

        StringBuilder sb  = new StringBuilder();
        for(int i = 0; i < totalSpaces/2; i++) {
            sb.append(" ");
        }
        sb.append(input);
        for(int i = 0; i < (totalSpaces+1)/2; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }



    @Override
    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard) {
        StringBuilder sb = new StringBuilder();

        //Enemy Title row
        sb.append("  ").append(padCenter("ENEMY BOARD", 2*enemyBoard.getWidth() + 5));
        sb.append("    ").append(padCenter("YOUR BOARD", 2*playerBoard.getWidth() + 5)).append("\n");

        //Player and Enemy alphabet row
        sb.append("     ");
        for(char i = 'A'; i < 'A' + enemyBoard.getWidth(); i++) {
            sb.append(i).append(" ");
        }
        sb.append("         ");
        for(char i = 'A'; i < 'A' + playerBoard.getWidth(); i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");

        //Grid separator (start)
        appendHorizontalBorder(playerBoard, enemyBoard, sb);

        //Enemy and player grid rows
        for(int y = 0; y < enemyBoard.getHeight(); y++) {
            int rowNumber = y+1;
            sb.append(String.format("%2d",rowNumber)).append(" | ");

            for(int x = 0; x < enemyBoard.getWidth(); x++) {
                CellState cellState = enemyBoard.getCellState(new Coordinates(x, y));
                switch (cellState) {
                    case NOTHING:
                        sb.append(". ");
                        break;
                    case MISS:
                        sb.append("M ");
                        break;
                    case SHIP_HIT:
                        sb.append("H ");
                        break;
                    default:
                        throw new IllegalStateException("Should not be able to see " + cellState + " on enemy board at x:" + x + " y:" + y);
                }
            }
            sb.append("|   ").append(String.format("%2d",rowNumber)).append(" | ");
            for(int x = 0; x < playerBoard.getWidth(); x++) {
                CellState cellState = playerBoard.getCellState(new Coordinates(x, y));
                switch(cellState) {
                    case NOTHING:
                        sb.append(". ");
                        break;
                    case MISS:
                        sb.append("M ");
                        break;
                    case SHIP_HIT:
                        sb.append("H ");
                        break;
                    case SHIP_NOT_HIT:
                        sb.append("# ");
                        break;
                    case SHIP_SUNK:
                        sb.append("S ");
                        break;
                    default:
                        throw new IllegalStateException("Should not be able to see " + cellState + " on player board at x:" + x + " y:" + y);
                }
            }
            sb.append("|\n");
        }

        appendHorizontalBorder(playerBoard, enemyBoard, sb);

        // Ships Remaining
        sb.append("  ").append(padCenter("Ships remaining: " + enemyBoard.getNumberOfShipsRemaining(), 2*enemyBoard.getWidth() + 5));
        sb.append("    ").append(padCenter("Ships remaining: " + playerBoard.getNumberOfShipsRemaining(), 2*playerBoard.getWidth() + 5)).append("\n");

        System.out.println(sb);
    }

    private void appendHorizontalBorder(PlayerBoard playerBoard, EnemyBoard enemyBoard, StringBuilder sb) {
        sb.append("   +");
        for(int x = 0; x < 2*enemyBoard.getWidth()+1; x++) {
            sb.append("-");
        }
        sb.append("+").append("      +");
        for(int x = 0; x < 2*playerBoard.getWidth()+1; x++) {
            sb.append("-");
        }
        sb.append("+\n");
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
