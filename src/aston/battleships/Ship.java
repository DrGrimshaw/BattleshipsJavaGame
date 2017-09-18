package aston.battleships;

/**
 * Created by cooperwd on 15/09/2017.
 */
public interface Ship {
    public int getLength();
    public boolean isAt(Coordinates coordinates);
    public void takeAHit();
    public boolean isSunk();
    public Coordinates getStartingPosition();
    public Orientation getOrientation();
}
