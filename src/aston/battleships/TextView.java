package aston.battleships;

import java.util.List;

/**
 * Created by cooperwd on 19/09/2017.
 */
public class TextView implements View {

    private static final String ENEMY_RESIGNED_TEXT =
            "\n             ~ ENEMY RESIGNED - YOU WIN ~\n" +
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
            "~-~-~-~-~-~    ~-~~-~-~-~-~    ========  ~-~-~-~";

    private static final String PLAYER_WON_TEXT =
            "\n                     ~ YOU WIN ~\n" +
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
            "~-~-~-~-~-~    ~-~~-~-~-~-~    ========  ~-~-~-~";

    private static final String PLAYER_LOST_TEXT =
            "\n        ~ YOU LOSE ~\n" +
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
            " _____.,-#%&$@%#&#~,._____ ";

    private static final String PLAYER_RESIGNED_TEXT =
            "\n~ YOU RESIGNED - ENEMY WINS ~\n" +
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
            " _____.,-#%&$@%#&#~,._____ ";



    private static String padCenter(String input, int newLength) {
        int totalSpaces = newLength - input.length();

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

        //Player and Enemy Title row
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
                sb.append(cellStateToString(cellState));
            }
            sb.append("|   ").append(String.format("%2d",rowNumber)).append(" | ");
            for(int x = 0; x < playerBoard.getWidth(); x++) {
                CellState cellState = playerBoard.getCellState(new Coordinates(x, y));
                sb.append(cellStateToString(cellState));
            }
            sb.append("|\n");
        }

        appendHorizontalBorder(playerBoard, enemyBoard, sb);

        // Ships Remaining
        sb.append("  ").append(padCenter("Ships remaining: "
                + enemyBoard.getNumberOfShipsRemaining(), 2*enemyBoard.getWidth() + 5));
        sb.append("    ").append(padCenter("Ships remaining: "
                + playerBoard.getNumberOfShipsRemaining(), 2*playerBoard.getWidth() + 5)).append("\n");

        System.out.println(sb);
    }

    private String cellStateToString(CellState state) {
        switch(state) {
            case NOTHING:
                return ". ";
            case MISS:
                return "M ";
            case SHIP_HIT:
                return "H ";
            case SHIP_NOT_HIT:
                return "# ";
            case SHIP_SUNK:
                return "S ";
            default:
                throw new IllegalStateException("Should not be able to see " + state + " on board");
        }
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
                "- You may also resign from a game by typing \"resign\"\n" +
                "~ GOOD LUCK PLAYER ~"
        );
    }

    @Override
    public void announceGameOver(GameOverMessage message) {
        switch(message) {
            case WON:
                System.out.println(PLAYER_WON_TEXT);
                break;
            case LOST:
                System.out.println(PLAYER_LOST_TEXT);
                break;
            case RESIGNED:
                System.out.println(PLAYER_RESIGNED_TEXT);
                break;
            case ENEMY_RESIGNED:
                System.out.println(ENEMY_RESIGNED_TEXT);
                break;
        }
    }

    @Override
    public void viewResultOfMove(Coordinates coordinates, CellState cellState) {
        //TODO: Different messages from a list for each state
        switch(cellState){
            case MISS:
                System.out.println("\n~ YOU MISSED ~\n");
                break;
            case SHIP_HIT:
                System.out.println("\n~ YOU HIT AN ENEMY SHIP! ~\n");
                break;
            case SHIP_SUNK:
                System.out.println("\n~ YOU SUNK THEIR BATTLESHIP ~\n");
                break;
            default:
                throw new IllegalArgumentException("Move cannot result in " + cellState);
        }
    }

    @Override
    public void viewResultOfEnemyMove(Coordinates coordinates, CellState cellState) {
        // TODO
    }

    @Override
    public void viewShipsLeftToPlace(List<Integer> shipLengths) {
        if(shipLengths.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\nShips left to place\n");
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
