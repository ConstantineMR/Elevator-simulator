public class Data {
    public boolean[] internalReq = new boolean[15];
    public boolean[] externalReq = new boolean[15];
    //public boolean[] age = new boolean[15];
    public int level;
    Direction direction;

    public enum Direction {U, D, S}

    Data(){
        for (int i = 0; i < 15; i++) {
            internalReq[i] = false;
            externalReq[i] = false;
        }
        level = 1;
        direction = Direction.S;
    }

}
