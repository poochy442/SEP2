package SDJ2Exam.state;

public class DoorStayOpen extends DoorState {
    @Override
    public void click(Door door) {
        door.setState(new DoorClosing());
    }
}
