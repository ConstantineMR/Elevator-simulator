public class ElevatorEng implements Runnable{
    static Data data;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(3000);
                if (data.direction == Data.Direction.U) {
                    if (data.level < 15) {
                        data.level++;
                    }
                }
                else if (data.direction == Data.Direction.D) {
                    if (data.level > 1) {
                        data.level--;
                    }
                }
                ElevatorGUI.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
