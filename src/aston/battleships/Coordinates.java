package aston.battleships;

public class Coordinates {
    public final int x;
    public final int y;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates (String coordinatesStr) {
        if(coordinatesStr.isEmpty()) {
            throw new IllegalArgumentException("No coordinates entered.");
        }

        char letter = coordinatesStr.charAt(0);
        char upperCaseLetter = Character.toUpperCase(letter);
        if(upperCaseLetter < 'A' || upperCaseLetter > 'Z') {
            throw new IllegalArgumentException("First character not between A and Z");
        } else {
            x = upperCaseLetter - 'A';
        }

        String numberStr = coordinatesStr.substring(1);
        try {
            int number = Integer.parseInt(numberStr);
            y = number - 1;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Malformatted number");
        }
    }

    public void checkBounds(int width, int height) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Coordinates (" + x + ", " + y + ") are out of bounds.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
