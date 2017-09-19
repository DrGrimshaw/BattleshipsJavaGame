package aston.battleships;

import java.util.List;

/**
 * Created by cooperwd on 19/09/2017.
 */
public class TextView implements View {

    private static String padCenter(String input, int newLength) {
        int inputLength = input.length();
        int spacesEitherSide = newLength-inputLength;
        StringBuilder sb  = new StringBuilder();

        boolean hasStringBeenAdded = false;

        for(int i = 1; i <= spacesEitherSide; i++) {
            if(i <= spacesEitherSide/2 || i >= (spacesEitherSide/2)+inputLength){
                sb.append(" ");
            } else if(!hasStringBeenAdded) {
                sb.append(input);
            }
        }

    }

    @Override
    public void viewBoards(PlayerBoard playerBoard, EnemyBoard enemyBoard) {
        int width = enemyBoard.getWidth();
        int height = enemyBoard.getHeight();
        StringBuilder sb = new StringBuilder();

        //TODO: Make enemy Title row
        // Get center width
        int spacesFromLeftToCenterOfEnemyBoard = ((width*3)+4)/2;
        int enemyTitlePosition = spacesFromLeftToCenterOfEnemyBoard-5;


        //TODO: Make enemy alphabet row
        //TODO: Make grid separator (start)
        //Make enemy and player grid rows
        for(int y = 0; y < enemyBoard.getHeight(); y++) {
            int rowNumber = y+1;

            sb.append(rowNumber + " | ");

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
            sb.append("|   ").append(rowNumber).append(" | ");
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
        //TODO: Make grid separator (end)

        //TODO: Make player Title row
        //TODO: Make player alphabet row
        //TODO: Make grid separator (start)
        //TODO: Make grid separator (end)

        System.out.println(sb);
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
