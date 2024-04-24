import java.util.Random;

public class Dice {
    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int face() {
        Random randomFace = new Random();

        return randomFace.nextInt(sides) + 1;
    }
}