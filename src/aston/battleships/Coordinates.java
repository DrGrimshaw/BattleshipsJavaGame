package aston.battleships;

public class Coordinates {
    public final int x;
    public final int y;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates (String coordinatesStr) {
        // TODO: check error cases
        char letter = coordinatesStr.charAt(0);
        char upperCaseLetter = Character.toUpperCase(letter);
        x = upperCaseLetter - 'A';

        String numberStr = coordinatesStr.substring(1);
        int number = Integer.parseInt(numberStr);
        y = number - 1;
    }


    public void checkBounds(int width, int height) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Coordinates (" + x + ", " + y + ") are out of bounds.");
        }
    }
}
