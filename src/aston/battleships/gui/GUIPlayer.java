package aston.battleships.gui;

import aston.battleships.*;

/**
 * Created by cooperwd on 21/09/2017.
 */
public class GUIPlayer extends AbstractPlayer {
    private final GUIView view;

    public GUIPlayer(int newBoardWidth, int newBoardHeight, int newShipsRemaining) {
        super(newBoardWidth, newBoardHeight, newShipsRemaining);
        view = new GUIView(newBoardWidth, newBoardHeight);
    }

    @Override
    public void placeShipOnToPlayerBoard(int lengthOfShip) throws ResignException {
        try {
            // TODO: keep asking until we get a valid move
            view.waitForShipStartPosition();
            // TODO: wait for orientation
            playerBoard.placeShip(new ShipImpl(lengthOfShip, view.startingPosition, view.orientation));
        } catch(PlayerBoard.ShipOverlapException e) {

        }
    }

    @Override
    public Coordinates chooseMove() throws QuitException {
        if(view.isClosed) {
            throw new ResignException();
        }

        // TODO: keep asking until we get a valid move
        view.waitForMove();
        return view.move;
    }

    @Override
    public View getView() {
        return view;
    }
}
