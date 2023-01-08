import java.util.concurrent.Semaphore;

public class Data {
    public int[] age = new int[15];
    public Semaphore semAge;
    public int level;
    Direction direction;

    public enum Direction {U, D, S}

    Data(){
        for (int i = 0; i < 15; i++) {
            age[i] = -1;
        }
        level = 1;
        direction = Direction.S;
        semAge = new Semaphore(1);
    }

}
