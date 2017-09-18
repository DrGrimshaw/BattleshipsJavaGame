package aston.battleships;

/**
 * Created by cooperwd on 18/09/2017.
 */
public class ShipImpl implements Ship {

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isAt(Coordinates coordinates) {
        return false;
    }

    @Override
    public void takeAHit() {

    }

    @Override
    public boolean isSunk() {
        return false;
    }
}
