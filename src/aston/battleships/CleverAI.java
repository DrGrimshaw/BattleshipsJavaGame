package aston.battleships;

/**
 * Created by lawtonel on 21/09/2017.
 */
public class CleverAI extends RandomPlayer {
    public enum State {
        EXPLORING,
        ATTACK_NORTH_GUESS, ATTACK_EAST_GUESS, ATTACK_SOUTH_GUESS,
        ATTACK_NORTH_KNOWN, ATTACK_EAST_KNOWN, ATTACK_SOUTH_KNOWN, ATTACK_WEST_KNOWN
    }

    private State currentState;
    private Coordinates startingPosition;

    public CleverAI(int newBoardWidth, int newBoardHeight, int newShipsRemaining) {
        super(newBoardWidth, newBoardHeight, newShipsRemaining);
        currentState = State.EXPLORING;
        startingPosition = null;
    }

    @Override
    public Coordinates chooseMove() {
        while(true) {
            int dx = 0, dy = 0;
            switch (currentState) {
                case EXPLORING:
                    return super.chooseMove();
                case ATTACK_NORTH_GUESS:
                case ATTACK_NORTH_KNOWN:
                    dy = -1;
                    break;
                case ATTACK_EAST_GUESS:
                case ATTACK_EAST_KNOWN:
                    dx = 1;
                    break;
                case ATTACK_SOUTH_GUESS:
                case ATTACK_SOUTH_KNOWN:
                    dy = 1;
                    break;
                case ATTACK_WEST_KNOWN:
                    dx = -1;
                    break;
                default:
                    throw new IllegalStateException();
            }
            Coordinates move = scan(dx, dy);
            if(move != null) {
                return move;
            } else {
                updateState(null, CellState.MISS);
            }
        }
    }

    private Coordinates scan(int dx, int dy) {
        Coordinates guess = new Coordinates(startingPosition.x + dx, startingPosition.y + dy);
        try {
            while (true) {
                CellState cellState = enemyBoard.getCellState(guess);
                if (cellState == CellState.NOTHING) {
                    return guess;
                } else if (cellState == CellState.SHIP_HIT) {
                    guess = new Coordinates(guess.x + dx, guess.y + dy);
                } else {
                    return null;
                }
            }
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    private void updateState(Coordinates coordinates, CellState cellState) {
        switch (cellState) {
            case MISS:
                switch (currentState) {
                    case ATTACK_NORTH_GUESS:
                        currentState = State.ATTACK_EAST_GUESS;
                        break;
                    case ATTACK_EAST_GUESS:
                        currentState = State.ATTACK_SOUTH_GUESS;
                        break;
                    case ATTACK_SOUTH_GUESS:
                        currentState = State.ATTACK_WEST_KNOWN;
                        break;
                    case ATTACK_NORTH_KNOWN:
                        currentState = State.ATTACK_SOUTH_KNOWN;
                        break;
                    case ATTACK_EAST_KNOWN:
                        currentState = State.ATTACK_WEST_KNOWN;
                        break;
                    case ATTACK_SOUTH_KNOWN:
                        currentState = State.ATTACK_EAST_KNOWN;
                        break;
                    case ATTACK_WEST_KNOWN:
                        currentState = State.ATTACK_SOUTH_KNOWN;
                }
                break;
            case SHIP_HIT:
                switch (currentState) {
                    case EXPLORING:
                        startingPosition = coordinates;
                        currentState = State.ATTACK_NORTH_GUESS;
                        break;
                    case ATTACK_NORTH_GUESS:
                        currentState = State.ATTACK_NORTH_KNOWN;
                        break;
                    case ATTACK_EAST_GUESS:
                        currentState = State.ATTACK_EAST_KNOWN;
                        break;
                    case ATTACK_SOUTH_GUESS:
                        currentState = State.ATTACK_SOUTH_KNOWN;
                        break;
                }
                break;
            case SHIP_SUNK:
                currentState = State.EXPLORING;
                startingPosition = null;
                break;
        }
    }

    @Override
    public View getView() {
        return new NullView() {
            @Override
            public void viewResultOfMove(Coordinates coordinates, CellState cellState) {
                updateState(coordinates, cellState);
            }
        };
    }
}
