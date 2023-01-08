public class ElevatorOS implements Runnable{
    static Data data;

    @Override
    public void run() {
        int ageing = 1;
        while (true) {
            try {
                data.semAge.acquire();
                data.age[data.level - 1] = -1;
                if ( ageing % 5 == 0 ) {
                    for (int i = 0; i < 15; i++) {
                        if (data.age[i] > 0) {
                            data.age[i]--;
                            ageing++;
                        }
                    }
                }
                else
                    ageing++;
                data.semAge.release();

                int min = Integer.MAX_VALUE, minIndex = -1;
                for (int i = 0; i < 15; i++) {
                    int h = this.heuristic(i);
                    if ( h > 0 && h < min ) {
                        minIndex = i;
                        min = h;
                    }
                }
                if (minIndex == -1)
                    data.direction = Data.Direction.S;
                else if (minIndex + 1 > data.level)
                    data.direction = Data.Direction.U;
                else
                    data.direction = Data.Direction.D;
                Thread.sleep(3000);
            } catch (Exception e){
                System.out.println("EXCEPTION");
            }
        }
    }

    public int heuristic( int index ){
        if ( data.age[index] < 0 )
            return -1;
        int value = 0;
        value += data.age[index] * 100;
        index++;
        if (data.direction == Data.Direction.D && data.level < index)
                    value += 10000;
        if (data.direction == Data.Direction.U && data.level > index)
                    value += 10000;
        value += Math.abs(data.level - index);
        return value;
    }

}
