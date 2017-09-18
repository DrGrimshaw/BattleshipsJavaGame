package aston.battleships;


public class Coordinates {
    public final int x;
    public final int y;
    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void checkBounds(int width, int height) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Coordinates (" + x + ", " + y + ") are out of bounds.");
        }
    }
}
