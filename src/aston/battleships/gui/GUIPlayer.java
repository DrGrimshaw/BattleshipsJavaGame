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
    public void placeShipOnToPlayerBoard(int lengthOfShip) {
        try {
            view.waitForShipPlacement();
            playerBoard.placeShip(new ShipImpl(lengthOfShip, view.startingPosition, view.orientation));
        } catch(PlayerBoard.ShipOverlapException e) {

        }
    }

    @Override
    public Coordinates chooseMove() throws QuitException {
        view.waitForMove();
        return view.move;
    }

    @Override
    public View getView() {
        return view;
    }
}
