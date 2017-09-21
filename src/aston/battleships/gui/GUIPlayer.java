package aston.battleships.gui;

import aston.battleships.AbstractPlayer;
import aston.battleships.Coordinates;
import aston.battleships.ShipImpl;
import aston.battleships.View;

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
        } catch() {

        }
    }

    @Override
    public Coordinates chooseMove() throws QuitException {
        return null;
    }

    @Override
    public View getView() {
        return view;
    }
}
