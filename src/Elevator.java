public class Elevator{

    public static void main(String[] args) {
        Data data = new Data();
        ElevatorGUI elevatorGUI = new ElevatorGUI();
        ElevatorGUI.data = data;
        ElevatorOS elevatorOS = new ElevatorOS();
        ElevatorOS.data = data;
        ElevatorEng elevatorEng = new ElevatorEng();
        ElevatorEng.data = data;
        Thread threadGUI = new Thread(elevatorGUI);
        Thread threadOS = new Thread(elevatorOS);
        Thread threadEng = new Thread(elevatorEng);
        threadGUI.start();
        threadOS.start();
        threadEng.start();

    }
}
