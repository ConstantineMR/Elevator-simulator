import java.util.concurrent.Semaphore;

public class Data {
    public int[][] age = new int[3][15];
    public Semaphore[] semAge = new Semaphore[3];
    public int[] level = new int[3];
    Direction[] direction = new Direction[3];

    public enum Direction {U, D, S}

    Data(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 15; j++) {
                age[i][j] = -1;
            }
            level[i] = 1;
            direction[i] = Direction.S;
            semAge[i] = new Semaphore(1);
        }
    }

}
