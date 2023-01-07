public class Data {
    public boolean[] floorReq = new boolean[15];
    public int[] atFloor = new int[15];

    Data(){
        for (int i = 0; i < 15; i++) {
            floorReq[i] = false;
            atFloor[i] = 0;
        }
    }

}
