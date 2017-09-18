package aston.battleships;

/**
 * Created by cooperwd on 18/09/2017.
 */
public class ShipImpl implements Ship {
    private final int length;
    private int health;
    private final Orientation orientation;
    private final Coordinates startingPosition;

    public ShipImpl(int length, Coordinates startingPosition, Orientation orientation) {
        if(length < 1) {
            throw new IllegalArgumentException("Incorrect length of: "+length);
        } else if(orientation == null) {
            throw new IllegalArgumentException("Null orientation");
        } else if(startingPosition.x < 0 || startingPosition.y < 0) {
            throw new IllegalArgumentException(
                    "Incorrect starting position"+
                    " for x: "+startingPosition.x+
                    " or y: "+startingPosition.y
            );
        } else {
            this.length = length;
            this.health = length;
            this.orientation = orientation;
            this.startingPosition = startingPosition;
        }
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean isAt(Coordinates coordinates) {
        switch(this.orientation){
            case DOWN:
                return coordinates.x == startingPosition.x
                    && coordinates.y >= startingPosition.y
                    && coordinates.y <= startingPosition.y+length-1;
            case RIGHT:
                return coordinates.y == startingPosition.y
                        && coordinates.x >= startingPosition.x
                        && coordinates.x <= startingPosition.x+length-1;
        }
        throw new IllegalStateException("Null orientation");
    }

    @Override
    public void takeAHit() {
        if(isSunk()){
            throw new IllegalStateException("Ship has already sunk.");
        }
        health--;
    }

    @Override
    public boolean isSunk() {
        return health == 0;
    }
}
