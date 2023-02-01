public class ElevatorEng implements Runnable{
    static Data data;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(4000);
                for (int i = 0; i < 3; i++) {
                    if (data.direction[i] == Data.Direction.U) {
                        if (data.level[i] < 15) {
                            data.level[i]++;
                        }
                    }
                    else if (data.direction[i] == Data.Direction.D) {
                        if (data.level[i] > 1) {
                            data.level[i]--;
                        }
                    }
                }
                ElevatorGUI.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
