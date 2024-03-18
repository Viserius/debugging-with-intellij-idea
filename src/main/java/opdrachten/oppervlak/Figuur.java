package opdrachten.oppervlak;

import java.util.Random;

public abstract class Figuur {
    protected static final Random rand = new Random();
    protected int area;

    public void setArea(int val) {
        area = val;
    }

    public int getArea() {
        return area;
    }

    public static Figuur getRandomFiguur() {
        return rand.nextBoolean() ? new Cirkel() : new Vierkant();
    }
}
