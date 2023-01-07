public class Elevator{

    public static void main(String[] args) {
        Data data = new Data();
        ElevatorGUI elevatorGUI = new ElevatorGUI();
        ElevatorGUI.data = data;
        ElevatorOS elevatorOS = new ElevatorOS();
        ElevatorOS.data = data;
        Thread threadGUI = new Thread(elevatorGUI);
        Thread threadOS = new Thread(elevatorOS);
        threadGUI.start();
        threadOS.start();

    }
}
