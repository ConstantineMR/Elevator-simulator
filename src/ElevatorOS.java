public class ElevatorOS implements Runnable{
    static Data data;

    @Override
    public void run() {
        //while (true){
            try {
                for (boolean b: data.floorReq) {
                    System.out.print(b + "\t");
                }
                System.out.println();
                for (int i: data.atFloor) {
                    System.out.print(i + "\t");
                }
                System.out.println();
                Thread.sleep(2000);
            } catch (Exception e){
                System.out.println("EXCEPTION");
            }
        //}
    }
}
