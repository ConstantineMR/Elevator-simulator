public class ElevatorOS implements Runnable{
    static Data data;

    @Override
    public void run() {
        while (true) {
            try {
                for (int k = 0; k < 3; k++) {
                    data.semAge[k].acquire();
                    data.age[k][data.level[k] - 1] = -1;
                    for (int i = 0; i < 15; i++) {
                        if (data.age[k][i] > 0) {
                            data.age[k][i]--;
                        }
                    }
                    data.semAge[k].release();

                    int min = Integer.MAX_VALUE, minIndex = -1;
                    for (int i = 0; i < 15; i++) {
                        int h = heuristic(i, k);
                        if (h > 0 && h < min) {
                            minIndex = i;
                            min = h;
                        }
                    }
                    if (minIndex == -1)
                        data.direction[k] = Data.Direction.S;
                    else if (minIndex + 1 > data.level[k])
                        data.direction[k] = Data.Direction.U;
                    else
                        data.direction[k] = Data.Direction.D;
                }
                Thread.sleep(4000);
            } catch (Exception e){
                System.out.println("EXCEPTION");
            }
        }
    }

    public static int heuristic( int index, int k ){
        if ( data.age[k][index] < 0 )
            return -1;
        int value = 0;
        value += data.age[k][index] * 100;
        index++;
        if (data.direction[k] == Data.Direction.D && data.level[k] < index)
                    value += 5000;
        if (data.direction[k] == Data.Direction.U && data.level[k] > index)
                    value += 5000;
        value += Math.abs(data.level[k] - index);
        return value;
    }

    public static int BestElevator( int index ){
        for (int i = 0; i < 3; i++) {
            if ( data.level[i] == index + 1 && data.direction[i] == Data.Direction.S)
                return i;
        }
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < 3; i++) {
            int h = 0;
            if (data.direction[i] == Data.Direction.D)
                if ( data.level[i] < index + 1 )
                    h += 100;
                else
                    h -= 100;
            if (data.direction[i] == Data.Direction.U)
                if ( data.level[i] > index + 1 )
                    h += 100;
                else
                    h -= 100;
            h += Math.abs(data.level[i] - ( index + 1 ) );
            if (h < min) {
                minIndex = i;
                min = h;
            }
        }
        return minIndex;
    }

}
