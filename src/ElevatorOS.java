public class ElevatorOS implements Runnable{
    static Data data;

    @Override
    public void run() {

        //while (true){
            try {
                for (boolean b: data.internalReq) {
                    System.out.print(b + "\t");
                }
                System.out.println();
                for (boolean i: data.externalReq) {
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
