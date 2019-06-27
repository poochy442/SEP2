package SDJ2Exam.state;

public class DoorClosed extends DoorState {


    @Override
    public void click(Door door) {
        door.setState(new DoorOpening());
    }

}
