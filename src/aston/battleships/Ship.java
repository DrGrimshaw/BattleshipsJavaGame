package aston.battleships;

/**
 * Created by cooperwd on 15/09/2017.
 */
public interface Ship {
    public int getLength();
    public boolean isAt(int x, int y);
    public void takeAHit();
    public boolean isSunk();
}
