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
        System.out.println(
                "       WELCOME TO BATTLESHIPS\n\n" +
                "                 __/__\\" + "\n" +
                "           _____/______|" + "\n" +
                "   _______/_____\\_______\\______"+ "\n" +
                "   \\              < < <       |\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void viewInstructions() {
        System.out.println(
                "INSTRUCTIONS\n" +
                "- Before the game starts you will be asked to place your battleships\n" +
                "- When the game starts you can select a position on the enemy board to fire on\n" +
                "- You cannot fire in the same place twice\n" +
                "- The game is only over when either you or your enemy have no battleships are left\n" +
                "- You may also resign from a game\n" +
                "~ GOOD LUCK PLAYER ~"
        );
    }

    @Override
    public void announceGameOver(GameOverMessage message) {
        switch(message) {
            case WON:
                System.out.println(
                        "                     ~ YOU WIN ~\n" +
                        "                                   .''.\n" +
                        "       .''.      .        *''*    :_\\/_:     .\n" +
                        "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
                        "  .''.: /\\ :    /)\\   ':'* /\\ *  : '..'.  -=:o:=-\n" +
                        " :_\\/_:'.:::.  | ' *''*    * '.\\'/.'_\\(/_'.':'.'\n" +
                        " : /\\ : :::::  =  *_\\/_*     -= o =- /)\\    '  *\n" +
                        "  '..'  ':::' === * /\\ *     .'/.\\'.  ' ._____\n" +
                        "      *        |   *..*         :       |.   |' .---\"|\n" +
                        "        *      |     _           .--'|  ||   | _|    |\n" +
                        "        *      |  .-'|       __  |   |  |    ||      |\n" +
                        "     .-----.   |  |' |  ||  |  | |   |  |    ||      |\n" +
                        " ___'       ' /\"\\ |  '-.\"\".    '-'   '-.'    '`      |____\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "                       ~-~-~-~-~-~-~-~-~-~   /|\n" +
                        "          )      ~-~-~-~-~-~-~-~  /|~       /_|\\\n" +
                        "        _-H-__  -~-~-~-~-~-~     /_|\\    -~======-~\n" +
                        "~-\\XXXXXXXXXX/~     ~-~-~-~     /__|_\\ ~-~-~-~\n" +
                        "~-~-~-~-~-~    ~-~~-~-~-~-~    ========  ~-~-~-~"
                );
                break;
            case LOST:
                System.out.println(
                        "        ~ YOU LOSE ~\n" +
                        "     _.-^^---....,,--\n" +
                        " _--                  --_\n" +
                        "<                        >)\n" +
                        "|                         |\n" +
                        " \\._                   _./\n" +
                        "    ```--. . , ; .--'''\n" +
                        "          | |   |\n" +
                        "       .-=||  | |=-.\n" +
                        "       `-=#$%&%$#=-'\n" +
                        "          | ;  :|\n" +
                        " _____.,-#%&$@%#&#~,._____ "
                );

                break;
            case RESIGNED:
                System.out.println(
                        "~ YOU RESIGNED - ENEMY WINS ~\n" +
                        "     _.-^^---....,,--\n" +
                        " _--                  --_\n" +
                        "<                        >)\n" +
                        "|                         |\n" +
                        " \\._                   _./\n" +
                        "    ```--. . , ; .--'''\n" +
                        "          | |   |\n" +
                        "       .-=||  | |=-.\n" +
                        "       `-=#$%&%$#=-'\n" +
                        "          | ;  :|\n" +
                        " _____.,-#%&$@%#&#~,._____ "
                );
                break;
            case ENEMY_RESIGNED:
                System.out.println(
                        "             ~ ENEMY RESIGNED - YOU WIN ~\n" +
                        "                                   .''.\n" +
                        "       .''.      .        *''*    :_\\/_:     .\n" +
                        "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
                        "  .''.: /\\ :    /)\\   ':'* /\\ *  : '..'.  -=:o:=-\n" +
                        " :_\\/_:'.:::.  | ' *''*    * '.\\'/.'_\\(/_'.':'.'\n" +
                        " : /\\ : :::::  =  *_\\/_*     -= o =- /)\\    '  *\n" +
                        "  '..'  ':::' === * /\\ *     .'/.\\'.  ' ._____\n" +
                        "      *        |   *..*         :       |.   |' .---\"|\n" +
                        "        *      |     _           .--'|  ||   | _|    |\n" +
                        "        *      |  .-'|       __  |   |  |    ||      |\n" +
                        "     .-----.   |  |' |  ||  |  | |   |  |    ||      |\n" +
                        " ___'       ' /\"\\ |  '-.\"\".    '-'   '-.'    '`      |____\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "                       ~-~-~-~-~-~-~-~-~-~   /|\n" +
                        "          )      ~-~-~-~-~-~-~-~  /|~       /_|\\\n" +
                        "        _-H-__  -~-~-~-~-~-~     /_|\\    -~======-~\n" +
                        "~-\\XXXXXXXXXX/~     ~-~-~-~     /__|_\\ ~-~-~-~\n" +
                        "~-~-~-~-~-~    ~-~~-~-~-~-~    ========  ~-~-~-~"
                );
                break;
        }
    }

    @Override
    public void viewResultOfMove(CellState cellState) {
        //TODO: Different messages from a list for each state
        switch(cellState){
            case MISS:
                System.out.println("~ YOU MISSED ~");
                break;
            case SHIP_HIT:
                System.out.println("~ YOU HIT AN ENEMY SHIP! ~");
                break;
            case SHIP_SUNK:
                System.out.println("~ YOU SUNK THEIR BATTLESHIP ~");
                break;
            default:
                throw new IllegalArgumentException("Move cannot result in " + cellState);
        }
    }

    @Override
    public void viewShipsLeftToPlace(List<Integer> shipLengths) {
        if(shipLengths.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ships Left to place\n\n");
            for (int shipLen : shipLengths) {
                sb.append("Ship of length -");
                for (int i = 0; i < shipLen; i++) {
                    sb.append(" #");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        } else {
            System.out.println("There are no more ships left to place.");
        }
    }
}
